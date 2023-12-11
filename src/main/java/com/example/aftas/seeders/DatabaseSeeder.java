package com.example.aftas.seeders;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // Here goes the seeders
    }
}
