package com.blackstar.jobag;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import com.blackstar.jobag.employeer.domain.repository.EmployeerRepository;
import com.blackstar.jobag.employeer.domain.service.EmployeerService;
import com.blackstar.jobag.employeer.service.EmployeerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeerServiceImplTest {

    @MockBean
    private EmployeerRepository employeerRepository;
    @Autowired
    private EmployeerService employeerService;

    @TestConfiguration
    static class EmployeerServiceImplTestConfiguration
    {
        @Bean
        public EmployeerService employeerService()
        {
            return new EmployeerServiceImpl();
        }
    }
    @Test
    @DisplayName("When getEmployeerById With Valid Title Then Returns Employeer")
    public void whenGetEmployeerByIdWithValidIdThenReturnsEmployeer() {
        // Arrange
        Long Id = 1L;
        Employeer employeer = new Employeer();
        when(employeerRepository.findById(Id))
                .thenReturn(Optional.of(employeer));

        // Act
        Employeer foundEmployeer = employeerService.getEmployeerById(Id);


    }

    @Test
    @DisplayName("When getEmployeerByPosicion With Valid Title Then Returns Employeer")
    public void whenGetEmployeerByPosicionWithValidPosicionThenReturnsEmployeer() {
        // Arrange
        String posicion = "aea";
        Employeer employeer = new Employeer().setPosicion(posicion);
        when(employeerRepository.findByPosicion(posicion))
                .thenReturn(Optional.of(employeer));

        // Act
       // Employeer foundEmployeer = employeerRepository.findByPosicion(posicion);

        // Assert
      //  assertThat(foundEmployeer.getPosicion()).isEqualTo(posicion);

    }




}