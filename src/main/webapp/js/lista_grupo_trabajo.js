$(document).ready(function () {
    
    buscarGrupos('grupos');
    
    detalleGrupo();
    borrarGrupo();
    
    cerrarModal('#cancelarModalGrupos');
    // Detectar cuando se carga la página desde el historial del navegador
    window.addEventListener('pageshow', function (event) {
        if (event.persisted || performance.getEntriesByType('navigation')[0].type === 'back_forward') {
            buscarGrupos('grupos'); // Volver a cargar los datos de grupos
        }
    });
});



function buscarGrupos(mensaje){
    $('#homeListaGrupos').empty();
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: mensaje},
        dataType: 'json',
        cache: false,
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
    $('#homeListaGrupos').empty();
    var rutaImagen = '/proyectoconstruccion/img/grupo.jpg';
    for(indice = 0; indice < lista.length; indice++){
        console.log(lista[indice]);
        var idGrupo = lista[indice].id_grupo;
        var nombreGrupo = lista[indice].nombre_grupo;
        var capataz = lista[indice].capataz;
        var cantidadEmpleados = 1 + lista[indice].empleados.length; // cantidad de empleados de la lista mas el capataz
        
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

    }
}

function detalleGrupo(){
    $('#homeListaGrupos').on('click', '.btnVerGrupo', function () {
        const idGrupo = $(this).attr('id').split('-')[1]; // Obtener el legajo del botón
        console.log('id: '+idGrupo);
        localStorage.setItem('detalleGrupo', idGrupo);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_grupo_trabajo.jsp";
    });
}

function borrarGrupo(){
    $('#homeListaGrupos').on('click', '.btnBorrarGrupo', function () {
        var idGrupo = $(this).attr('id').split('-')[1]; // Obtener el id del botón
        console.log('borrar: '+idGrupo);
        localStorage.setItem('borrarGrupo', idGrupo);
        $('#cancelarModalGrupos').show();
        $('#borrarModalGrupos').show();
        mensajeModalBorrar();
    });
}

function mensajeModalBorrar(){
    $('#contenedorTextoModal').empty();
    $('#contenedorTextoModal').append('<p>¿Desea borrar este grupo?</p>');
    $('#mensajeModalListaGrupos').show();
    $('#cerrarModalGrupos').hide();
    borrarModal();
}

function borrarModal(){
    $('#borrarModalGrupos').click(function(){
        $('#contenedorTextoModal').empty();
        //console.log('Borrar: '+idGrupo);
        
        $('#cerrarModalGrupos').hide();
        $('#cancelarModalGrupos').show();
        $('#borrarModalGrupos').show();
        
        solicitudBorrar();
        cerrarModal('#cerrarModalGrupos');
        
    });
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalListaGrupos').hide();
    });
}

function solicitudBorrar(){
    var idGrupo = localStorage.getItem('borrarGrupo');
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: 'borrar', idBorrar: idGrupo},
        dataType: 'json',
        success: function (response) {
            console.log(response);
            $('#contenedorTextoModal').empty();
            $('#contenedorTextoModal').append('<p>'+response.mensaje+'</p>');
            $('#cerrarModalGrupos').show();
            $('#cancelarModalGrupos').hide();
            $('#borrarModalGrupos').hide();
            
            $('#homeListaGrupos').empty();
            buscarGrupos('grupos');
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}