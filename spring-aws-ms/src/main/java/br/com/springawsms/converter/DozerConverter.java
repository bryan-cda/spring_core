package br.com.springawsms.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <S, T> T parseObject(S source, Class<T> target){
        return mapper.map(source, target);
    }

    public static <S, T> List<T> parseListObjects(List<S> source, Class<T> target){
        List<T> listObjects = new ArrayList<>();

        for(Object o : source){
            listObjects.add(mapper.map(o, target));
        }
        return listObjects;
    }
}
