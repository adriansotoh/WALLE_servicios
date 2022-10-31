package com.walle.controller;

import com.walle.entity.Trabajador;
import com.walle.impl.TrabajadorServiceImp;
import com.walle.utils.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TrabajadorController {

    @Autowired
    TrabajadorServiceImp serviceTrabajador;

    /*-----------METODO PENSADO PARA COMBO BOX / BARRA DE BÚSQUEDA */
    @GetMapping("/buscarPorRol/{idRol}")
    @ResponseBody
    public List<Trabajador> buscarPorRolBusqueda(@PathVariable String idRol) {
        int idRolToInt = Integer.parseInt(idRol);
        List<Trabajador> list = serviceTrabajador.listarPorRol(idRolToInt);

        return list;
    }
}
