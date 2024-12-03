$(document).ready(function(){
    console.log('detalle grupo');
    
    var grupo = JSON.parse(localStorage.getItem('detalleGrupo'));
    idGrupo = grupo.id_grupo;
    console.log(grupo);
    
    insertarDetalleGrupo(grupo);
    detalleEmpleado();
    borrarEmpleadoDelGrupo();
    
    cerrarModal('#cancelarModalGrupo');
    cerrarModal('#aceptarModalGrupo');
});

var idGrupo = 0;

function insertarDetalleGrupo(grupo){
    var nombre = grupo.nombre_grupo;
    var capataz = grupo.capataz;
    var empleados = grupo.empleados;
    var clase = 'impar';
    
    //insertar capataz
    $('#contenedorCapataz').append('<div>'+
                                        '<p>'+capataz.apellidos+'</p>'+
                                    '</div>');
    
    // insertar empleados
    for(indice=0; indice<empleados.length; indice++){
        if(indice%2 === 0){
            clase = 'impar';
        }
        else{
            clase = 'par';
        }
        
        $('#contenedorIntegrantes').append('<div class="empleado '+clase+'">'+
                                                '<p>'+empleados[indice].legajo+'</p>'+
                                                '<p>'+empleados[indice].apellidos+', '+empleados[indice].nombres+'</p>'+
                                                '<p>'+empleados[indice].cuil+'</p>'+
                                                '<p>'+empleados[indice].jerarquia.descripcion+'</p>'+
                                                '<p>'+empleados[indice].fecha_ingreso+'</p>'+
                                                '<div class="botonesEmpleado">'+
                                                    '<span class="btnIcono btnDetalleEmpleado" id="ver-'+empleados[indice].id_empleado+'" data-idEmpleado="'+empleados[indice].id_empleado+'">'+
                                                        '<i class="bx bx-search-alt icon"></i>'+
                                                    '</span>'+
                                                    '<span class="btnIcono btnBorrarEmpleado" id="borrar-'+empleados[indice].id_empleado+'" data-idEmpleado="'+empleados[indice].id_empleado+'">'+
                                                        '<i class="bx bx-trash icon"></i>'+
                                                    '</span>'+
                                                '</div>'+
                                            '</div>');
    }  
}

function detalleEmpleado(){
    $('#contenedorIntegrantes').on('click', '.btnDetalleEmpleado', function () {
        const idEmpleado = $(this).data('idempleado'); // Obtener el legajo del botón
        console.log(idEmpleado);
        localStorage.setItem('detalleEmpleado', idEmpleado);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_empleado.jsp";
    });
}

function borrarEmpleadoDelGrupo(){
    $('#contenedorIntegrantes').on('click', '.btnBorrarEmpleado', function () {
        const idEmpleado = $(this).data('idempleado'); // Obtener el legajo del botón
        mensajeModalBorrarEmpleado(idEmpleado);
    });
}

function mensajeModalBorrarEmpleado(idEmpleado){
    $('#contenedorTextoModal').empty();
    $('#contenedorTextoModal').append('<p>¿Desea borrar este empleado?</p>');
    $('#mensajeModalDetalleGrupo').show();
    $('#aceptarModalGrupo').hide();
    borrarEmpleado(idEmpleado);
}

function borrarEmpleado(idEmpleado){
    $('#eliminarModalGrupo').click(function(){
        console.log('eliminar: '+idEmpleado);
        $('#contenedorTextoModal').empty();
        $('#contenedorTextoModal').append('<p>El empleado se ha borrado del grupo</p>');
        
        $('#cancelarModalGrupo').hide();
        $('#eliminarModalGrupo').hide();
        $('#aceptarModalGrupo').show();
        
        $.ajax({
            url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
            type: 'GET',
            data: {mensaje: 'borrarEmpleado', idBorrarEmpleado: idEmpleado},
            dataType: 'json',
            success: function (response) {
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalDetalleGrupo').hide();
        if(idBoton === '#aceptarModalGrupo'){
            $('#contenedorCapataz').empty();
            $('#contenedorIntegrantes').empty();
            cargarGrupo(idGrupo);
        }
    });
}

function cargarGrupo(idGrupo){
    console.log('ecargar grupo: '+idGrupo);
}