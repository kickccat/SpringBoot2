package io.sample.blog.service;

import io.sample.blog.dao.TypeRepository;
import io.sample.blog.exceptions.NotFoundException;
import io.sample.blog.model.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    
    private final TypeRepository typeRepository;
    
    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
    
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    
    @Override
    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }
    
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
    
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }
    
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }
    
    @Override
    public Type updateTypeById(Long id, Type type) {
        Optional<Type> type1 = typeRepository.findById(id);
        if (!type1.isPresent()) {
            throw new NotFoundException("该类型不存在");
        }
        BeanUtils.copyProperties(type, type1.get());
        return typeRepository.save(type1.get());
    }
    
    @Override
    public void deleteTypeById(Long id) {
        typeRepository.deleteById(id);
    }
}
