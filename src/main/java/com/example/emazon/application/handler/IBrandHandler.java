// application.handler.IBrandHandler
package com.example.emazon.application.handler;

import com.example.emazon.application.dto.BrandRequest;
import com.example.emazon.domain.utils.PageCustom;


public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
    PageCustom<BrandRequest> listBrands(int page, int size, String sortOrder);
}
