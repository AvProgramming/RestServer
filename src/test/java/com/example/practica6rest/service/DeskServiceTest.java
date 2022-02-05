package com.example.practica6rest.service;

import com.example.practica6rest.model.Desk;
import com.example.practica6rest.repository.DeskRepository;
import com.example.practica6rest.service.impl.DeskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeskServiceTest extends MockitoRunner {

    @Mock
    private DeskRepository deskRepository;

    @Mock
    private DeskService deskService;

    @Before
    public void setUp() {
        deskService = new DeskServiceImpl(deskRepository);
    }

    @Test
    public void shouldInitialiseRepository() {
        assertNotNull(deskRepository);
    }

    @Test
    public void shouldInitialiseService() {
        assertNotNull(deskService);
    }

    @Test
    public void shouldGetAll() {
        List<Desk> expected = new ArrayList<>();
        expected.add(new Desk(1L, 1, 5));
        expected.add(new Desk(2L, 2, 6));
        expected.add(new Desk(3L, 3, 1));

        when(deskRepository.findAll()).thenReturn(expected);

        List<Desk> desks = deskService.getAll();
        assertEquals(expected, desks);
        verify(deskRepository).findAll();

    }

    @Test
    public void shouldGetById() {
        Desk desk = new Desk(1L, 1, 5);
        when(deskRepository.existsById(anyLong())).thenReturn(true);
        when(deskRepository.getById(anyLong())).thenReturn(desk);
        deskService.getById(1L);
        verify(deskRepository).getById(anyLong());
    }

    @Test
    public void shouldRegistry() {
        Desk deskBom = new Desk(1, 5);
        Desk desk = new Desk(1L, 1, 5);
        when(deskRepository.save(any())).thenReturn(desk);
        deskService.registry(deskBom);
        verify(deskRepository).save(any(Desk.class));
    }

    @Test
    public void shouldDelete() {
        when(deskRepository.existsById(anyLong())).thenReturn(true);
        deskService.delete(2L);
        verify(deskRepository).deleteById(anyLong());
    }

    @Test
    public void shouldUpdate() {
        Desk deskNew = new Desk(2, 10);
        Desk deskOld = new Desk(1L, 1, 5);
        when(deskRepository.existsById(anyLong())).thenReturn(true);
        when(deskRepository.getById(anyLong())).thenReturn(deskOld);
        when(deskRepository.saveAndFlush(any())).thenReturn(deskOld);
        Desk deskUpdated = deskService.update(deskNew, 1L);

        assertEquals(deskOld.getId(), deskUpdated.getId());
        assertEquals(deskOld.getNumber(), deskUpdated.getNumber());
        assertEquals(deskNew.getMax_capacity(), deskUpdated.getMax_capacity());

        verify(deskRepository).saveAndFlush(any(Desk.class));
    }

    @Test
    public void shouldThrowExceptionGetById() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            deskService.getById(1L);
        });
        String expectedMessage = "NO SUCH DESK";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
