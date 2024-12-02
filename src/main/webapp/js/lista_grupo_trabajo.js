$(document).ready(function(){    
    buscarGrupos('grupos');
    
    cerrarModal('#cancelarModalGrupos');
});

function buscarGrupos(mensaje){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: mensaje},
        dataType: 'json',
        success: function (response) {
            if(response.mensaje){
                console.log(response);
                $('#homeListaGrupos').append('<article> <h1>No existen grupos de trabajo</h1> </article>');
            }
            else{
                insertarGrupos(response);
            }
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
        var capataz = lista[indice].capataz;
        var cantidadEmpleados = lista[indice].empleados.length; // cantidad de empleados de la lista mas el capataz
        
        if(lista[indice].nombre_grupo === '' || lista[indice].nombre_grupo === undefined){
            nombreGrupo = 'Sin nombre';
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
        mensajeModalBorrar(idGrupo.split('-')[1]);
    });
}

function mensajeModalBorrar(idGrupo){
    $('#contenedorTextoModal').empty();
    $('#contenedorTextoModal').append('<p>¿Desea borrar este grupo?</p>');
    $('#mensajeModalListaGrupos').show();
    $('#cerrarModalGrupos').hide();
    borrarModal(idGrupo);
}

function borrarModal(idGrupo){
    $('#borrarModalGrupos').click(function(){
        $('#contenedorTextoModal').empty();
        console.log('Borrar: '+idGrupo);
        
        $('#cerrarModalGrupos').show();
        $('#cancelarModalGrupos').hide();
        $('#borrarModalGrupos').hide();
        
        solicitudBorrar(idGrupo);
        
        cerrarModal('#cerrarModalGrupos');
    });
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalListaGrupos').hide();
        if(idBoton === '#cerrarModalGrupos'){
            $('#homeListaGrupos').empty();
            buscarGrupos('grupos');
        }
    });
}

function solicitudBorrar(idGrupo){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: 'borrar', idBorrar: idGrupo},
        dataType: 'json',
        success: function (response) {
            console.log(response);
            $('#contenedorTextoModal').empty();
            $('#contenedorTextoModal').append('<p>'+response.mensaje+'</p>');
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}