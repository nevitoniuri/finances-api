package com.nevitoniuri.financesapi.util;

public interface Assemblable<R, E> {

    E toEntity(R request);

}
