package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.TicketEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTicketRepository extends GenericJpaRepository<Long, TicketEntity>
        implements TicketRepository {

}

