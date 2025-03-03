package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.domain.HomeCategorySection;
import com.ecommerce.ecommerce_multivendor.entity.Deal;
import com.ecommerce.ecommerce_multivendor.entity.HomeCategory;
import com.ecommerce.ecommerce_multivendor.entity.HomePage;
import com.ecommerce.ecommerce_multivendor.repository.DealRepository;
import com.ecommerce.ecommerce_multivendor.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final DealRepository dealRepository;

    @Override
    public HomePage createHomePageData(List<HomeCategory> allCategories) {
        List<HomeCategory>gridCategories = allCategories.stream()
                .filter(homeCategory ->
                        homeCategory.getSection()== HomeCategorySection.GRID)
                .collect(Collectors.toList());
        List<HomeCategory>shopByCategories = allCategories.stream()
                .filter(homeCategory ->
                        homeCategory.getSection()== HomeCategorySection.SHOP_BY_CATEGORY)
                .collect(Collectors.toList());
        List<HomeCategory>electricCategories = allCategories.stream()
                .filter(homeCategory ->
                        homeCategory.getSection()== HomeCategorySection.ELECTRIC_CATEGORY)
                .collect(Collectors.toList());
        List<HomeCategory>dealCategories = allCategories.stream()
                .filter(homeCategory ->
                        homeCategory.getSection()== HomeCategorySection.DEALS)
                .collect(Collectors.toList());

        List <Deal> createDeals = new ArrayList<>();
        if(dealRepository.findAll().isEmpty()){
            List<Deal> deals = allCategories.stream()
                    .filter(homeCategory -> homeCategory.getSection() == HomeCategorySection.DEALS)
                    .map(homeCategory -> new Deal(null,10,homeCategory))
                    .collect(Collectors.toList());
            createDeals = dealRepository.saveAll(deals);
        }else createDeals = dealRepository.findAll();

        HomePage homePage = new HomePage();
        homePage.setGrid(gridCategories);
        homePage.setShopByCategories(shopByCategories);
        homePage.setElectricCategories(electricCategories);
        homePage.setDeals(createDeals);
        homePage.setDealCategories(dealCategories);
        return homePage;
    }
}
