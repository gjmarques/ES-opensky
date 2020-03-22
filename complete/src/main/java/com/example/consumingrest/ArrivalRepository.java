package com.example.consumingrest;


import org.springframework.data.repository.CrudRepository;

import com.example.consumingrest.Arrival;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ArrivalRepository extends CrudRepository<Arrival, Integer> {


}
