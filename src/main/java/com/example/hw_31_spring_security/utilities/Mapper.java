package com.example.hw_31_spring_security.utilities;

import com.google.common.collect.Streams;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Mapper {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static <E, D> D toDto(@NonNull final E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <E, D> List<D> allToDto(@NonNull final Collection<E> entityCollection, Class<D> dtoClass) {
        return entityCollection.stream()
                .map(e -> modelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    public static <E, D> List<D> iterableToDto(@NonNull final Iterable<E> entityCollection, Class<D> dtoClass) {
        return Streams.stream(entityCollection)
                .map(e -> modelMapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

    public static <E, D> E toEntity(@NonNull final D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public static <E, D> List<E> allToEntity(@NonNull final Collection<D> dtoCollection, Class<E> entityClass) {
        return dtoCollection.stream()
                .map(d -> modelMapper.map(d, entityClass))
                .collect(Collectors.toList());
    }
}
