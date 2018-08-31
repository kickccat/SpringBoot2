package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Wallet;

public interface WalletService {
    
    Wallet findById(Long id);
}
