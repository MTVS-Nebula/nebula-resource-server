package com.nebula.nebula_resource.helper.generator;


import com.nebula.nebula_resource.app.dao.entity.avatar.Avatar;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.BaseClothes;
import com.nebula.nebula_resource.app.dao.entity.item.clothes.Clothes;
import com.nebula.nebula_resource.app.dao.repository.item.BaseClothesRepository;
import com.nebula.nebula_resource.app.dao.repository.item.ClothesRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RandomClothesGenerator {
    private static final int SUCCESS_PERCENTAGE = 40;
    private static BaseClothesRepository baseClothesRepository;
    private static ClothesRepository clothesRepository;
    private static Random random = new Random();

    @Autowired
    private RandomClothesGenerator(BaseClothesRepository baseClothesRepository,
                                  ClothesRepository clothesRepository) {
        RandomClothesGenerator.baseClothesRepository = baseClothesRepository;
        RandomClothesGenerator.clothesRepository = clothesRepository;
    }


    public static Clothes generateClothes(Avatar avatar){
        if(random.nextInt(100) < SUCCESS_PERCENTAGE){
            return null;
        }
        int totalNum = baseClothesRepository.countBy();
        int randomIndex = random.nextInt(totalNum) + 1;
        BaseClothes baseClothes = baseClothesRepository.findById(randomIndex);
        Clothes clothes = new Clothes(0,baseClothes,null, avatar);
        clothesRepository.save(clothes);
        return clothes;
    }

}
