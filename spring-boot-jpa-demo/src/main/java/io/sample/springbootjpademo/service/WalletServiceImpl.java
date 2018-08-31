package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Wallet;
import io.sample.springbootjpademo.domain.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    
    private final WalletRepository walletRepository;
    
    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }
    
    @Override
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }
}
