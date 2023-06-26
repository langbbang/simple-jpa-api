package me.songha.simple.product;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import me.songha.simple.product.dto.ProductCreationRequest;
import me.songha.simple.product.dto.ProductResponse;
import me.songha.simple.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse findProductById(@NotNull Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new).toProductResponse();
    }

    public ProductResponse create(@NotNull ProductCreationRequest creationReq) {
        return productRepository.save(creationReq.toEntity()).toProductResponse();
    }

    @Transactional
    public ProductResponse modify(@NotNull ProductUpdateRequest updateRequest) {
        return productRepository.findById(updateRequest.getId())
                .orElseThrow(ProductNotFoundException::new)
                .updateProduct(updateRequest)
                .toProductResponse();
    }

}
