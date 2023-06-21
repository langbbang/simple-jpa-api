package me.songha.simple.product;

import lombok.RequiredArgsConstructor;
import me.songha.simple.product.dto.ProductCreationRequest;
import me.songha.simple.product.dto.ProductResponse;
import me.songha.simple.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductResponse findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new).toProductResponse();
    }

    public ProductResponse create(ProductCreationRequest creationReq) {
        return productRepository.save(creationReq.toEntity()).toProductResponse();
    }

    public ProductResponse modify(ProductUpdateRequest updateRequest) {
        return productRepository.findById(updateRequest.getId())
                .orElseThrow(ProductNotFoundException::new)
                .updateProduct(updateRequest)
                .toProductResponse();
    }

}
