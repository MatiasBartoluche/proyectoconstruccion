$(document).ready(function(){
    console.log('detalle grupo');
    
    var idGrupo = localStorage.getItem('detalleGrupo');
    
    cargarGrupo(idGrupo);
    
    detalleEmpleado();
    borrarEmpleadoDelGrupo();
    
    cerrarModal('#cancelarModalGrupo');
    cerrarModal('#aceptarModalGrupo');
    
    detalleCapataz();
    cambiarCapataz();
    
    modalAceptarCambioCapataz();
    modalCambiarCapataz();
    modalCancelarCapataz();

});

function insertarDetalleGrupo(grupo){
    var nombre = grupo.nombre_grupo;
    var capataz = grupo.capataz;
    var empleados = grupo.empleados;
    var clase = 'impar';
    
    //insertar capataz
    $('#btnDetalleCapataz').attr('value', capataz.id_empleado);
    $('#dniCapataz').attr('src', `data:image/png;base64,${capataz.foto_dni_base64}`);
    $('#datosCapataz').append('<p>Nombre: '+capataz.apellidos+', '+capataz.nombres+'</p>');
    $('#datosCapataz').append('<p>Cuil: '+capataz.cuil+'</p>');
    $('#datosCapataz').append('<p>Fecha de ingreso: '+capataz.fecha_ingreso+'</p>');
    $('#datosCapataz').append('<p>Numero de legajo: '+capataz.legajo+'</p>');
    
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
            cache: false,
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
            $('#contenedorIntegrantes').empty();
            cargarGrupo(localStorage.getItem('detalleGrupo'));
        }
    });
}

function cargarGrupo(idGrupo){
    console.log('ecargar grupo: '+idGrupo);
    
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: 'recargarGrupo', idGrupo: idGrupo},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response);
            if(response.mensaje){
                
            }
            else{
                insertarDetalleGrupo(response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function detalleCapataz(){
    $('#btnDetalleCapataz').click(function(){
        var idCapataz = $('#btnDetalleCapataz').attr('value');
        console.log('detalle capataz: '+idCapataz);
        localStorage.setItem('detalleEmpleado', idCapataz);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_empleado.jsp";
    });
}

function cambiarCapataz(){
    $('#btnCambiarCapataz').click(function(){
        var idCapataz = $('#btnDetalleCapataz').attr('value');
        console.log('cambiar capataz:'+idCapataz);
        
        $('#modalCambiarCapataz').show();
        $('#aceptarModalCapataz').hide();
        
        $('#listaCambiarCapataz').show();
        
        $('#cambiarModalCapataz').show();
        $('#cancelarModalCapataz').show();
        
        cargarCapataz();
    });
}

function modalAceptarCambioCapataz(){
    $('#aceptarModalCapataz').click(function(){
        console.log('aceptar');
        $('#modalCambiarCapataz').hide();
        $('#CancelarModalCapataz').hide();
        
        $('#listaCambiarCapataz').hide();
    });
}

function modalCambiarCapataz(){
    $('#cambiarModalCapataz').click(function(){
        console.log('cambiar');
        $('#aceptarModalCapataz').show();
        $('#cambiarModalCapataz').hide();
        $('#cancelarModalCapataz').hide();
    });
}

function modalCancelarCapataz(){
    $('#cancelarModalCapataz').click(function(){
        console.log('cancelar');
        $('#modalCambiarCapataz').hide();
    });
}

function cargarCapataz(){
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: 'capataz'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            insertarCapataces(response);
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener empleados:', error);
        }
    });
}

function insertarCapataces(listaEmpleados){
    console.log(listaEmpleados);
}

/*
        $(idContenedor).append('<div class="empleado '+clase+'">'+
                                    '<p>'+item.legajo+'</p>'+
                                    '<p>'+item.apellidos+', '+item.nombres+'</p>'+
                                    '<p>'+item.cuil+'</p>'+
                                    '<p>'+item.jerarquia.descripcion+'</p>'+
                                    '<p>'+item.fecha_ingreso+'</p>'+
                                    '<div class="botonesEmpleado" id"check-'+item.id_empleado+'">'+
                                       '<input type="checkbox" id="'+item.id_empleado+'" value="'+item.id_empleado+'">'+
                                    '</div>'+
                                '</div>');
*/