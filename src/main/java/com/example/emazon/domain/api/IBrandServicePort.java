//domain.api.IBrandServicePort
package com.example.emazon.domain.api;

import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.utils.PageCustom;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PageCustom<Brand> listBrands(int page, int size, String sortOrder);
}
