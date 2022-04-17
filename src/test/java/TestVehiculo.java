import com.svalero.concesionario.domain.Vehiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

public class TestVehiculo {
    String referencia = "TestReferencia";
    String marca = "TestMarca";
    String modelo = "TestModelo";
    Integer plazas = 5;
    Integer precioBase = 10000;

    Vehiculo vehiculo1 = new Vehiculo(referencia, marca, modelo, plazas, precioBase);
    Vehiculo vehiculo2 = new Vehiculo(marca, modelo, plazas, precioBase);

    @Test
    public void testGeneracionVehiculo1(){
        boolean res = false;
        if(Objects.equals(vehiculo1.getReferencia(), referencia)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo2(){
        boolean res = false;
        if(Objects.equals(vehiculo1.getMarca(), marca)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo3(){
        boolean res = false;
        if(Objects.equals(vehiculo1.getModelo(), modelo)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo4(){
        boolean res = false;
        if(Objects.equals(vehiculo1.getPlazas(), plazas)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo5(){
        boolean res = false;
        if(Objects.equals(vehiculo1.getPrecioBase(), precioBase)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo6(){
        boolean res = false;
        if(Objects.equals(vehiculo2.getMarca(), marca)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo7(){
        boolean res = false;
        if(Objects.equals(vehiculo2.getModelo(), modelo)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo8(){
        boolean res = false;
        if(Objects.equals(vehiculo2.getPlazas(), plazas)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionVehiculo9(){
        boolean res = false;
        if(Objects.equals(vehiculo2.getPrecioBase(), precioBase)){
            res = true;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUUID1(){
        boolean res = true;
        try {
            UUID uuid = UUID.fromString(vehiculo2.getReferencia());
        } catch (IllegalArgumentException iae){
            res = false;
        }
        Assertions.assertTrue(res);
    }

    @Test
    public void testGeneracionUUID2(){
        boolean res = true;
        try {
            UUID uuid = UUID.fromString(vehiculo1.getReferencia());
        } catch (IllegalArgumentException iae){
            res = false;
        }
        Assertions.assertFalse(res);
    }
}
