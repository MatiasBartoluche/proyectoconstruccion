$(document).ready(function(){
    console.log('grupos');
    
    buscarGrupos('grupos');
});

function buscarGrupos(mensaje){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: mensaje},
        dataType: 'json',
        success: function (response) {
            insertarGrupos(response);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function insertarGrupos(lista){
    var rutaImagen = '/proyectoconstruccion/img/grupo.jpg';
    for(indice = 0; indice < lista.length; indice++){
        console.log(lista[indice]);
        var idGrupo = lista[indice].id_grupo;
        var nombreGrupo = lista[indice].nombre_grupo;
        var capataz = '';
        var cantidadEmpleados = lista[indice].empleados.length;
        
        if(lista[indice].nombre_grupo === '' || lista[indice].nombre_grupo === undefined){
            nombreGrupo = 'Sin nombre';
        }
        
        for(indiceEmpleados = 0; indiceEmpleados < lista[indice].empleados.length; indiceEmpleados++){
            if(lista[indice].empleados[indiceEmpleados].jerarquia.descripcion === 'Capataz'){
                capataz = lista[indice].empleados[indiceEmpleados];
            }
            //indiceEmpleados = indiceEmpleados + 1;
        }

        $('#homeListaGrupos').append('<article class="modulo grupos">'+
                                        '<div class="contenedorImagen">'+
                                            '<img src="'+rutaImagen+'">'+
                                        '</div>'+
                                        '<div class="infoGrupo">'+
                                            '<p>' + nombreGrupo + '</p>' +
                                            '<p>Capataz: ' + capataz.apellidos + ', ' + capataz.nombres + '</p>' +
                                            '<p>Cantidad de empleados: ' + cantidadEmpleados + '</p>' +
                                        '</div>'+
                                        '<div class="botonesGrupo">'+
                                            '<span class="btnIcono btnVerGrupo" id="ver-'+idGrupo+'" data-idGrupo="'+idGrupo+'">'+
                                                '<i class="bx bx-search-alt icon"></i>'+
                                            '</span>'+
                                            '<span class="btnIcono btnBorrarGrupo" id="borrar-'+idGrupo+'" data-idGrupo="'+idGrupo+'">'+
                                                '<i class="bx bx-trash icon"></i>'+
                                            '</span>'+
                                        '</div>'+
                                    '</article>');
        detalleGrupo(lista[indice]);
        borrarGrupo(lista[indice]);
    }
}

function detalleGrupo(grupo){
    $('#homeListaGrupos').on('click', '.btnVerGrupo', function () {
        const idGrupo = $(this).attr('id'); // Obtener el legajo del botón
        console.log('accion: '+idGrupo.split('-')[0]);
        console.log('id: '+idGrupo.split('-')[1]);
        localStorage.setItem('detalleGrupo', JSON.stringify(grupo));
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_grupo_trabajo.jsp";
    });
}

function borrarGrupo(grupo){
    $('#homeListaGrupos').on('click', '.btnBorrarGrupo', function () {
        const idGrupo = $(this).attr('id'); // Obtener el legajo del botón
        console.log('accion: '+idGrupo.split('-')[0]);
        console.log('id: '+idGrupo.split('-')[1]);
        //localStorage.setItem('detalleEmpleado', idEmpleado);
        //window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_empleado.jsp";
    });
}