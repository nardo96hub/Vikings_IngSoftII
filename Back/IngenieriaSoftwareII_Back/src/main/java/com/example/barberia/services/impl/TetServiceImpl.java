package com.example.barberia.services.impl;

import com.example.barberia.model.*;

import com.example.barberia.repositories.*;

import com.example.barberia.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class TetServiceImpl implements TestService {

    @Autowired
    private ProductoRepository repoProd;

    @Autowired
    private TurnoRepository repoTurno;

    @Autowired
    private ContactoRepository repoCont;

    @Autowired
    PersonaRepository repoUser;

    @Autowired
    RolRepository repoRol;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public Boolean setAllInfo() {

        Boolean tur,prod,per,con;

        per = setPersona();
        if (per){
            tur = setTurno();
            if (tur){
                prod = setProducto();
                if (prod){
                    con = setContacto();
                    return con;
                }

            }
        }

        return false;
    }

    private Boolean setTurno(){

        if (repoTurno.count() == 0){
            LocalDate fehca = LocalDate.now();
            LocalDate fechaInicio = fehca.plusDays(-15);
            LocalDate fechaFin = fehca.plusDays(15);

            List<String> barberos = Arrays.asList(new String[]{"Alfredo","Ignacio","Miguel"});

            List<String> userName = Arrays.asList(new String[]{"admin","user"});

            Map<String,String> usuarios = new HashMap();
            usuarios.put("admin","admin@admin");
            usuarios.put("user","user@user");

            //List<Boolean> disp = Arrays.asList(new Boolean[]{true,false});
            //Persona user = repoUser.findByEmail(usuarios.get(numRandom(1))).get();


            Integer dia,mes,horaInicio,horaFin;

            while (fechaInicio.isBefore(fechaFin.plusDays(1))){
                dia = fechaInicio.getDayOfMonth();
                mes = fechaInicio.getMonthValue();
                DayOfWeek dia_semana = fechaInicio.getDayOfWeek();
                String textm = "";
                String texth = "";
                if (dia_semana != DayOfWeek.SUNDAY){
                    if (dia_semana == DayOfWeek.SATURDAY){
                        horaInicio = 8;
                        horaFin = 13;
                    }else {
                        horaInicio = 9;
                        horaFin = 21;
                    }
                    for (int i = horaInicio; i <horaFin;i++){
                        for(int j=0;j<2;j++){
                            if(i<10) texth="0"+i;
                            else texth = Integer.toString(i);

                            if(j==0) textm ="00";
                            else textm = "30";

                            Turno turno;
                            String usuario = userName.get(numRandom(1));

                            if (usuario.equals("admin")){
                                turno = new Turno(usuario,usuarios.get(usuario),barberos.get(numRandom(barberos.size()-1)),dia,mes,texth+":"+textm,true);
                            } else {
                                turno = new Turno(usuario,usuarios.get(usuario),barberos.get(numRandom(barberos.size()-1)),dia,mes,texth+":"+textm,false);
                            }

                            repoTurno.save(turno);
                        }
                    }
                }
                fechaInicio = fechaInicio.plusDays(1);
            }
            return true;
        }

        return false;
    }

    private Boolean setProducto(){
        if (repoProd.count() == 0){
            List<String> proveedores = Arrays.asList(new String[]{"Proveedor Bruno","Proveedor Diego","Proveedor Luciano"});
            List<Boolean> disponible = Arrays.asList(new Boolean[]{true,false});

            repoProd.save(new Producto("Cera BARBER","Cera Efecto Brillante BARBER x 60ml Primont",540.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod1.jpg"));
            repoProd.save(new Producto("Gel BARBER", "Gel Efecto Fuerte BARBER x 250ml Primont", 540.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod2.jpg"));
            repoProd.save(new Producto ("Pomada BARBER", "Pomada Efecto Mate BARBER x 60ml Primont", 540.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod3.jpg"));

            repoProd.save(new Producto("Serum BARBER", "Serum Con Aceite De Argán x 20ml Primont", 435.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod4.jpg"));
            repoProd.save(new Producto("Shampoo BARBER", "Shampoo BARBER x 250ml Primont", 540.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod5.jpg"));
            repoProd.save(new Producto("Acondicionador BARBER", "Acondicionador x 60ml Primont", 435.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod6.jpg"));

            repoProd.save(new Producto("Ampolla P07", "Ampolla Caída Severa P07 x 10ml Primont", 99.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod7.jpg"));
            repoProd.save(new Producto("Shampoo For Men", "Shampoo For Men Caída x 250ml Primont", 225.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod8.jpg"));
            repoProd.save(new Producto("Combo Novelty", "Set BARBER Novelty + REGALO! Primont", 2500.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod9.jpg"));

            repoProd.save(new Producto("Ampollas Caída Severa", "Caja De 6 Unidades P07 x 10ml Primont", 500.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda3.jpg"));
            repoProd.save(new Producto("Shampoo Queration", "Shampoo Queration x 350ml Primont", 400.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod13.jpg"));
            repoProd.save(new Producto("Máscara Capilar", "Máscara Capilar Con Células Madres x 237gr", 659.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod14.jpg"));

            repoProd.save(new Producto("Gel Fijador Fijación Natural", "Gel Fijador Para Cabellos Sin Alcohol x 1050gr Fijación Natural De Preal", 269.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod10.jpg"));
            repoProd.save(new Producto("Gel Fijador Efecto Húmedo", "Gel Fijador Para Cabello Sin Alcohol x 1050gr Efecto Húmedo De Preal", 269.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod11.jpg"));
            repoProd.save(new Producto("Gel Fijador Fijación Fuerte", "Gel Fijador Para Cabello Sin Alcohol x 500gr Fijación Fuerte De Preal", 162.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod12.jpg"));

            repoProd.save(new Producto("Crema de afeitar", "Crema de afeitar con mentol x 200gr De Preal", 300.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda5.jpg"));
            repoProd.save(new Producto("Fijador De Cabello De Preal", "Fijador Ricinado Para El Cabello. Fijacion + Brillo x 125ml De Preal", 290.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda12.jpg"));
            repoProd.save(new Producto("Gel Fijador Cabello Fijación Fuerte", "Gel Fijador Para El Cabello Sin Alcohol Fijación Fuerte x 250ml De Preal", 139.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda15.jpg"));

            repoProd.save(new Producto("Gel Fijador Brillo Mojado", "Gel Fijador Para El Cabello Sin Alcohol Brillo Mojado Extra Fijación x 250ml De Preal", 139.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda16.jpg"));
            repoProd.save(new Producto("Gel Fijador Fijación Natural", "Gel Fijador Para El Cabello Sin Alcohol Fijación Natural x 250ml De Preal", 139.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod15.jpg"));
            repoProd.save(new Producto("Gel Fijador Efecto Húmedo", "Gel Fijador Para El Cabello Sin Alcohol Efecto Húmedo x 250ml De Preal", 139.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod16.png"));

            repoProd.save(new Producto("Cera En Pomada", "Cera En Pomada Modelar Fijación Tecni Art Stiff x 75ml L''oreal En Colaboración Con De Preal", 1380.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda4.jpg"));
            repoProd.save(new Producto("Gel Fijador Fix Max", "Gel Fijador Tecni Art Fix Max x 200ml L''oreal En Colaboración Con De Preal", 1415.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda13.jpg"));
            repoProd.save(new Producto("Colonia Floral", "Colonia Clásica Floral x 500ml De Preal", 240.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/prod17.jpg"));

            repoProd.save(new Producto("Aceite Multifunción Para Barba Y Afeitado", "Aceite Multifunción x 50ml Con Extractos: Biológico De Dátil,Aceite De Girasol y Almendra Dulce", 320.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda1.jpg"));
            repoProd.save(new Producto("Limpiador Para Barba", "Limpiador Para Barba x 250ml Recomendado Para Limpieza Diaria Con Extractos Biológicos De Dátil, Nuez Y De Jengibre", 200.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda2.jpg"));
            repoProd.save(new Producto("Crema Emoliente Post Afeitada", "Crema Emoliente Para Después De Afeitar x 100ml Con Extractos de: Biológico de Dátil,Aceite de Neem y Biológico de Mirto", 260.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda6.jpg"));

            repoProd.save(new Producto("Limpiador Para El Cuerpo Y El Cabello", "Limpiador Para El Cuerpo Y El Cabello x 250ml Con Extracto Biológico de Dátil + Aceite de Árbol de Té + Extracto Biológico de Aloe", 586.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda17.jpg"));
            repoProd.save(new Producto("Crema Colorante Fitoproteica Hydra-Color", "Crema Con Extractos De Hydra-Color Blend + Fitoqueratina Vegetal + Aceite Biológico De Argán + Manteca De Semillas De Uva", 450.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda29.png"));
            repoProd.save(new Producto("Combo Alisante", "Crema Alisante Para Cabellos Naturales O Resistentes + Fórmula Disciplinante Alisante De Acción Prolongada, Antiencrespado", 1250.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda30.jpg"));

            repoProd.save(new Producto("Shampoo Nutritivo", "Shampoo Nutritivo Para Cabellos Secos Y Frágiles Con Extracto Biológico De Avena + Fitocomplejo Nutritivo (Aceite De Coco, Manteca De Illipé, Aceite De Almendra Dulce)", 730.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda31.jpg"));
            repoProd.save(new Producto("Complejo Reestructurante", "Suero Reestructurante Y Protector Con Aceite De Rosa Mosqueta + Fitocomplejo Biológico Reparador (Extractos Biológicos De Malva, Malvavisco y Caléndula)", 500.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda32.jpg"));
            repoProd.save(new Producto("Shampoo Antioxidante", "Shampoo Antioxidante Con Extracto Biológico De Zanahoria + Fitocomplejo Antioxidante (Manteca De Soja, Aceite De Jojoba, Aceite de Avellana)", 736.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda33.jpg"));

            repoProd.save(new Producto("Crema Lenitiva Para El Pelo", "Crema Calmante Y Lenitiva Para El Pelo Irritado O Enrojecido Con Extractos De: Chumbera, Biológicos De Aloe Y De Malva", 750.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda34.jpg"));
            repoProd.save(new Producto("Máscara Reestructurante", "Máscara Reestructurante Para Revitalizar El Cabello Dañado Y Debilitado Con Extracto Biológico De Germen De Trigo + Fitocomplejo Reestructurante", 400.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda35.jpg"));
            repoProd.save(new Producto("Máscara Para Cuero Cabelludo Sensible", "Para Dejarlo Más Suave Y Con Más Cuerpo, con Extracto Biológico de Calabaza y de Membrillo", 625.00f, disponible.get(numRandom(1)), 200.00f, 50, proveedores.get(numRandom(proveedores.size()-1)),"./assets/imagenes/tienda36.jpg"));

            return true;
        }
        return false;}
    private Boolean setPersona(){

        if (repoUser.count() == 0){
            List<Persona> personas = new ArrayList<>();
            Rol adminRole = repoRol.findByName(ERol.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

            Rol userRole = repoRol.findByName(ERol.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

            Persona persona = new Persona("admin","admin@admin", encoder.encode("admin1234"));
            Set<Rol> roles = new HashSet<>();roles.add(adminRole);
            persona.setRoles(roles); personas.add(persona);

            persona = new Persona("user","user@user", encoder.encode("user1234"));
            roles = new HashSet<>();roles.add(userRole);
            persona.setRoles(roles); personas.add(persona);

            for (Persona p : personas)repoUser.save(p);

            return true;
        }
        return false;
    }
    private Boolean setContacto(){
        if (repoCont.count() == 0){
            repoCont.save(new Contacto("Back","End","back@end","123456789","Este mensaje se genero del Back"));
            repoCont.save(new Contacto("BaCk","EnD","BaCk@EnD","987654321","Mensaje generado por Back"));

            return true;
        }
        return false;
    }

    private int numRandom(Integer max){
        return (int)( Math.round(Math.random() * max));
    }
}
