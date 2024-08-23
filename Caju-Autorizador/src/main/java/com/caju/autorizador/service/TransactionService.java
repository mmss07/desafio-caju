package com.caju.autorizador.service;

import com.caju.autorizador.dominio.Account;
import com.caju.autorizador.dto.RespostaDTO;
import com.caju.autorizador.dto.TransactionDTO;
import com.caju.autorizador.respository.IAccountRepository;
import com.caju.autorizador.respository.IClientRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.caju.autorizador.constant.Constantes.*;


@Service
public class TransactionService {

    private IAccountRepository iAccountRepository;

    @Autowired
    public TransactionService(IAccountRepository iAccountRepository, IClientRepository iClientRepository) {
        this.iAccountRepository = iAccountRepository;
    }

    public ResponseEntity<RespostaDTO> debitAccount1(TransactionDTO transactionDTO){
        var respostaDTOResponseEntity = iAccountRepository.findById(transactionDTO.getAccount()).map(account -> {
            var body = validaDebitL1(account, transactionDTO);
            return ResponseEntity.ok(body);
        });
        return respostaDTOResponseEntity.orElseGet(() -> ResponseEntity.ok(new RespostaDTO("07")));

    }

    public ResponseEntity<RespostaDTO> debitAccount2(TransactionDTO transactionDTO){
        var respostaDTOResponseEntity = iAccountRepository.findById(transactionDTO.getAccount()).map(account -> {
            var body = validaDebitL2(account, transactionDTO);
            return ResponseEntity.ok(body);
        });
        return respostaDTOResponseEntity.orElseGet(() -> ResponseEntity.ok(new RespostaDTO("07")));

    }

    private RespostaDTO validaDebitL1(Account accountRecords, TransactionDTO transactionDTO) {
       return switch (transactionDTO.getMcc()){
            case MCC_5411, MCC_5412 -> debitAccountL1(accountRecords, FOOD, transactionDTO.getTotalAmount());
            case MCC_5811, MCC_5812 -> debitAccountL1(accountRecords, MEAL, transactionDTO.getTotalAmount());
            default -> debitAccountL1(accountRecords, CASH, transactionDTO.getTotalAmount());
        };
    }

    private RespostaDTO validaDebitL2(Account accountRecords, TransactionDTO transactionDTO) {
        return switch (transactionDTO.getMcc()){
            case MCC_5411, MCC_5412 -> debitAccountL2(accountRecords, FOOD, transactionDTO.getTotalAmount());
            case MCC_5811, MCC_5812 -> debitAccountL2(accountRecords, MEAL, transactionDTO.getTotalAmount());
            default -> debitAccountL2(accountRecords, CASH, transactionDTO.getTotalAmount());
        };
    }

    @Transactional
    public RespostaDTO debitAccountL1(Account accountRecords, String saldo, double valorDebit){
       return switch (saldo){
            case FOOD -> {
                if(accountRecords.getBalanceFood() >= valorDebit){
                    accountRecords.setBalanceFood(accountRecords.getBalanceFood() - valorDebit);
                    iAccountRepository.save(accountRecords);
                    yield new RespostaDTO(CODE_SUCESSO);
                }
                yield new RespostaDTO(CODE_REJEITADA);
            }
           case MEAL -> {
               if(accountRecords.getBalanceMeal() >= valorDebit ){
                   accountRecords.setBalanceMeal(accountRecords.getBalanceMeal() - valorDebit);
                   iAccountRepository.save(accountRecords);
                   yield new RespostaDTO(CODE_SUCESSO);
               }
               yield new RespostaDTO(CODE_REJEITADA);
           }
           case CASH -> debitCash(accountRecords, valorDebit);
            default -> new RespostaDTO(CODE_OUTRO);
        };


    }

    @Transactional
    public RespostaDTO debitAccountL2(Account accountRecords, String saldo, double valorDebit){
        return switch (saldo){
            case FOOD -> {
                if(accountRecords.getBalanceFood() >= valorDebit){
                    accountRecords.setBalanceFood(accountRecords.getBalanceFood() - valorDebit);
                    iAccountRepository.save(accountRecords);
                    yield new RespostaDTO(CODE_SUCESSO);
                }else{
                    yield debitCash(accountRecords, valorDebit);
                }

            }
            case MEAL -> {
                if(accountRecords.getBalanceMeal() >= valorDebit ){
                    accountRecords.setBalanceMeal(accountRecords.getBalanceMeal() - valorDebit);
                    iAccountRepository.save(accountRecords);
                    yield new RespostaDTO(CODE_SUCESSO);
                }else {
                    yield debitCash(accountRecords, valorDebit);
                }

            }
            case CASH -> debitCash(accountRecords, valorDebit);
            default -> new RespostaDTO(CODE_OUTRO);
        };
    }

    private RespostaDTO debitCash(Account accountRecords, double valorDebit){
        if(accountRecords.getBalanceCash() >= valorDebit ){
            accountRecords.setBalanceCash(accountRecords.getBalanceCash() - valorDebit);
            iAccountRepository.save(accountRecords);
            return new RespostaDTO(CODE_SUCESSO);
        }
        return new RespostaDTO(CODE_REJEITADA);
    }

    public ResponseEntity<RespostaDTO> debitAccount3(TransactionDTO transactionDTO){
        return debitAccountL3(transactionDTO);
    }

    @Transactional
    public ResponseEntity<RespostaDTO> debitAccountL3(TransactionDTO transactionDTO){
        if(transactionDTO.getAccount() != 0
                && (StringUtils.isNotEmpty(transactionDTO.getMcc()) && validaMcc(transactionDTO.getMcc()))){
            Optional<Account> byId = iAccountRepository.findById(transactionDTO.getAccount());
            if(byId.isPresent()){
                return debitAccount2(transactionDTO);
            }else{
                return ResponseEntity.ok(new RespostaDTO("07"));
            }
        }

        Optional<Account> byClientByName = iAccountRepository.findByClientByName(transactionDTO.getMerchant());
        var retorno =  byClientByName.map(accountRecords ->
             switch (accountRecords.getClient().getMcc()){
                case MCC_5411, MCC_5412 -> {
                    if(accountRecords.getBalanceFood() >= transactionDTO.getTotalAmount()){
                        accountRecords.setBalanceFood(accountRecords.getBalanceFood() - transactionDTO.getTotalAmount());
                        iAccountRepository.save(accountRecords);
                        yield new RespostaDTO(CODE_SUCESSO);
                    }
                    yield debitCash(accountRecords, transactionDTO.getTotalAmount());
                }
                case MCC_5811, MCC_5812 -> {
                    if(accountRecords.getBalanceMeal() >= transactionDTO.getTotalAmount() ){
                        accountRecords.setBalanceMeal(accountRecords.getBalanceMeal() - transactionDTO.getTotalAmount());
                        iAccountRepository.save(accountRecords);
                        yield new RespostaDTO(CODE_SUCESSO);
                    }
                    yield debitCash(accountRecords, transactionDTO.getTotalAmount());
                }
                default -> debitCash(accountRecords, transactionDTO.getTotalAmount());

            }
        );
        return retorno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new RespostaDTO("07")));

    }

    private boolean validaMcc(String mcc){
        return mcc.contains(MCCS);
    }
}
