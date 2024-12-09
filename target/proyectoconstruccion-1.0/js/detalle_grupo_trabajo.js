$(document).ready(function(){
    
    // cuando se accede a la pagina desde el historial del navegador, refresca las funciones
    window.addEventListener('pageshow', function (event) {
        if (event.persisted || performance.getEntriesByType('navigation')[0].type === 'back_forward') {
            var idGrupo = localStorage.getItem('detalleGrupo');
            cargarGrupo(idGrupo);
        }
    });
    
    $('#cambiarCapataz').hide();
    var idGrupo = localStorage.getItem('detalleGrupo');

    cargarGrupo(idGrupo);
    detalleEmpleado();
    borrarEmpleado();
    
    modalEliminar();
    
    detalleCapataz();
    detalleEmpleado();
    
    desplegarListaCapataces();
    cancelarCapataz();
    
    cambiarCapataz();
    
    cerrarModal('#btnModalAceptar');
    
    agregarEmpleados();
    cancelarEmpleados();
    aceptarEmpleados();
});

function insertarDetalleGrupo(grupo){
    $('#datosCapataz').empty();
    $('#dniCapataz').attr('src', '');
    $('#contenedorIntegrantes').empty();
    
    var idGrupo = grupo.id_grupo;
    var nombre = grupo.nombre_grupo;
    var capataz = grupo.capataz;
    var empleados = grupo.empleados;
    var clase = 'impar';
    
    
    $('#detalleGrupo').attr('value', idGrupo);
    //insertar capataz
    $('#contenedorCapataz').attr('value', capataz.id_empleado);
    $('#btnDetalleCapataz').attr('value', capataz.id_empleado);
    $('#datosCapataz').append('<p>Nombre: '+capataz.apellidos+', '+capataz.nombres+'</p>');
    $('#datosCapataz').append('<p>Cuil: '+capataz.cuil+'</p>');
    $('#datosCapataz').append('<p>Fecha de ingreso: '+capataz.fecha_ingreso+'</p>');
    $('#datosCapataz').append('<p>Numero de legajo: '+capataz.legajo+'</p>');
    
    if(capataz.foto_dni_base64){
        //$('#contenedorDni').css('background-image' url('ruta/de/la/imagen.jpg');
        $('#dniCapataz').attr('src', `data:image/png;base64,${capataz.foto_dni_base64}`);
    }
    
        const img = document.querySelector('img');
        console.log('Dimensiones de la imagen:', img.naturalWidth, img.naturalHeight);
    
    if(empleados.length === 0){
        $('#contenedorIntegrantes').append('<h1>Este grupo no contiene empleados</h1>');
    }
    else{
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
}

function detalleEmpleado(){
    $('#contenedorIntegrantes').on('click', '.btnDetalleEmpleado', function () {
        const idEmpleado = $(this).data('idempleado'); // Obtener el legajo del botón
        console.log(idEmpleado);
        localStorage.setItem('detalleEmpleado', idEmpleado);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_empleado.jsp";
    });
}

function borrarEmpleado(){
    $('#contenedorIntegrantes').off('click').on('click', '.btnBorrarEmpleado', function () {
        const idEmpleado = $(this).data('idempleado'); // Obtener el legajo del botón
        console.log('Borrar empleado del grupo: '+idEmpleado);
        localStorage.setItem('retirarEmpleado', idEmpleado);
        mensajeModal('¿Desea retirar este empleado del grupo de trabajo?', false, false, true, true);
        cerrarModal('#btnModalCancelar');
    });
}

function modalEliminar(){
    $('#btnModalBorrar').off('click').click(function(){
        var idGrupo = parseInt($('#detalleGrupo').attr('value'));
        var idEmpleado = localStorage.getItem('retirarEmpleado');
        console.log('++++++++ borrando: '+idEmpleado);
        
        $.ajax({
            url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
            type: 'GET',
            data: {mensaje: 'borrarEmpleado', idBorrarEmpleado: idEmpleado},
            dataType: 'json',
            cache: false,
            success: function (response) {
                console.log(response);
                mensajeModal(response.mensaje, false, true, false, false);
                cargarGrupo(idGrupo);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
}

function cargarGrupo(idGrupo){
    console.log('recargar grupo: '+idGrupo);
    
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: 'recargarGrupo', idGrupo: idGrupo},
        dataType: 'json',
        cache: false,
        success: function (response) {
            if(response.mensaje){
                console.log(response.mensaje);
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

function insertarCapataces(listaCapataces){
    $('#listaCambiarCapataz').empty();
    
    $('#btnDetalleCapataz').hide();
    $('#btnCambiarCapataz').hide();
    
    $('#btnAceptarCapataz').show();
    $('#btnCancelarCapataz').show();
    
    $('#cambiarCapataz').append('<h2>Seleccione un nuevo capataz</h2>');
    var clase = 'impar';
    var intercambiar = false;
    var disponible = 'Disponible';
    var capatazActual = parseInt($('#contenedorCapataz').attr('value'));
    
    if(listaCapataces.length === 0){
        $('#listaCapataces').append('<h1>No existen capataces</h1>');
    }
    else{
        for(indice = 0; indice < listaCapataces.length; indice++){
            if(indice%2 === 0){
                clase = 'impar';
            }
            else{
                clase = 'par';
            }

            if(listaCapataces[indice].grupoDTO){
                intercambiar = true;
                disponible = 'Ocupado';
            }
            else{
                intercambiar = false;
                disponible = 'Disponible';
            }

            if(listaCapataces[indice].id_empleado !== capatazActual){
                $('#listaCapataces').append('<div class="empleado '+clase+'">'+
                                                        '<p>'+listaCapataces[indice].legajo+'</p>'+
                                                        '<p>'+listaCapataces[indice].apellidos+', '+listaCapataces[indice].nombres+'</p>'+
                                                        '<p>'+listaCapataces[indice].cuil+'</p>'+
                                                        '<p>'+listaCapataces[indice].fecha_ingreso+'</p>'+
                                                        '<p id="disponible-'+listaCapataces[indice].id_empleado+'" value="'+intercambiar+'">'+disponible+'</p>'+
                                                        '<div class="botonesEmpleado" id"check-'+listaCapataces[indice].id_empleado+'">'+
                                                           '<input type="radio" name="capataz" id="'+listaCapataces[indice].id_empleado+'" value="'+listaCapataces[indice].id_empleado+'">'+
                                                        '</div>'+
                                                    '</div>');
            }
        }
    }
    

}

function desplegarListaCapataces(){
    $('#btnCambiarCapataz').click(function(){
        // muestro la lista de capataces
        $('#listaCapataces').show();  
        // oculto los botones
        $('#btnCambiarCapatas').hide();
        // llamo a la funcion de carga de capataces para llenar #listaCapataces
        cargarCapataz();
    });
}

function cancelarCapataz(){
    $('#btnCancelarCapataz').click(function(){
        $('#listaCapataces').hide();
        $('#listaCapataces').empty();

        $('#btnAceptarCapataz').hide();
        $('#btnCancelarCapataz').hide();
        $('#btnCambiarCapataz').show();
        $('#btnDetalleCapataz').show();
    });
}

function cambiarCapataz(){
    $('#btnAceptarCapataz').off('click').click(function(){
        var capatazActual = $('#contenedorCapataz').attr('value');
        var nuevoCapataz = $('input[name="capataz"]:checked').val();
        var intercambiar = $('#disponible-'+nuevoCapataz).attr('value');
        console.log(intercambiar);
        
        if(nuevoCapataz === undefined){
            mensajeModal('Debe seleccionar un capataz', false, true, false, false);
        }
        else{
            var cambiarCapataz = {actual: capatazActual, nuevo: nuevoCapataz, intercambiar: intercambiar};
            if(intercambiar === true){
                var mensaje = 'El capataz seleccionado ya pertenece a un grupo ¿Desea intercambiar los capataces de ambos grupos?';
                mensajeModal(mensaje, true, false, true, false);
                solicitudCambiarCapataz(cambiarCapataz);
                cerrarModal('#btnModalCancelar');
            }
            else{
                mensajeModal('¿desea reemplazar el capataz actual?' ,true, false, true, false);
                solicitudCambiarCapataz(cambiarCapataz);
                cerrarModal('#btnModalCancelar');
            }
        }
    });
}

// se le pasa como parametro tres boolean, para determinar la visualizacion del boton
function mensajeModal(mensaje, btnCambiar, btnAceptar, btnCancelar, btnEliminar){
    // borrar el mensaje anterior, si es que lo tiene
    $('#contenedorTextoModal').empty();
    
    $('#mensajeModalDetalleGrupo').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    
    //
    if(btnCambiar === true){
        $('#btnModalCambiarCapataz').show();
    }
    else{
        $('#btnModalCambiarCapataz').hide();
    }
    
    if(btnAceptar === true){
        $('#btnModalAceptar').show();
    }
    else{
        $('#btnModalAceptar').hide();
    }
    
    if(btnCancelar === true){
        $('#btnModalCancelar').show();
    }
    else{
        $('#btnModalCancelar').hide();
    }
    
    if(btnEliminar === true){
        $('#btnModalBorrar').show();
    }
    else{
        $('#btnModalBorrar').hide();
    }
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalDetalleGrupo').hide();
    });
}

function solicitudCambiarCapataz(cambiar){
    $('#btnModalCambiarCapataz').off('click').click(function(){
        $.extend(cambiar, {mensaje: 'cambiar'});
        console.log(cambiar);
        
        $.ajax({
            url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
            type: 'POST',
            data: cambiar,
            dataType: 'json',
            cache: false,
            success: function (response) {
                console.log(response);
                if(response.status === true){
                    mensajeModal(response.mensaje, false, true, false, false);
                    cargarNuevoCapataz(response.capataz);
                }
                else{
                    mensajeModal(response.mensaje, false, true, false, false);
                }
            },
            error: function (xhr, status, error) {
                console.error('Error al obtener empleados:', error);
            }
        });
    });
}

function cargarNuevoCapataz(nuevoCapataz){
    //vaciar todos los elementos
    $('#listaCapataces').hide();
    $('#btnAceptarCapataz').hide();
    $('#btnCancelarCapataz').hide();
    $('#btnDetalleCapataz').show();
    $('#btnCambiarCapataz').show();
    
    
    console.log('-------- insertando nuevo capataz');
    $('#datosCapataz').empty();
    $('#dniCapataz').attr('src','');
    
    //insertar capataz
    $('#contenedorCapataz').attr('value', nuevoCapataz.id_empleado);
    $('#btnDetalleCapataz').attr('value', nuevoCapataz.id_empleado);
    $('#datosCapataz').append('<p>Nombre: '+nuevoCapataz.apellidos+', '+nuevoCapataz.nombres+'</p>');
    $('#datosCapataz').append('<p>Cuil: '+nuevoCapataz.cuil+'</p>');
    $('#datosCapataz').append('<p>Fecha de ingreso: '+nuevoCapataz.fecha_ingreso+'</p>');
    $('#datosCapataz').append('<p>Numero de legajo: '+nuevoCapataz.legajo+'</p>');
    
    if(nuevoCapataz.foto_dni_base64){
        $('#dniCapataz').attr('src', `data:image/png;base64,${nuevoCapataz.foto_dni_base64}`);
    }
}

function agregarEmpleados(){
    $('#btnAgregarEmpleados').click(function(){
        $('#btnAgregarEmpleados').hide();
        $('#nuevosEmpleados').empty();
        $('#contenedorNuevosEmpleados').show();
        
        $('#btnAceptarEmpleados').show();
        $('#btnCancelarEmpleados').show();
        
        $.ajax({
            url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
            type: 'GET',
            data: {mensaje: 'empleados'},
            dataType: 'json',
            cache: false,
            success: function (response) {
                insertarNuevosEmpleados(response.empleados);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
}

function insertarNuevosEmpleados(listaEmpleados){
    console.log(listaEmpleados);
    var idGrupo = parseInt($('#detalleGrupo').attr('value'));
    var clase = 'impar';
    var disponible = 'Disponible';
    var booleanDisponible = true;
         ////$('#nuevosEmpleados').append('<h1>No existen empleados</h1>');
    for (indice = 0; indice < listaEmpleados.length; indice++) {
        if (indice % 2 === 0) {
            clase = 'impar';
        } else {
            clase = 'par';
        }

        if (listaEmpleados[indice].grupoDTO) {
            disponible = 'Ocupado';
            booleanDisponible = false;
        } else {
            disponible = 'Disponible';
            booleanDisponible = true;
        }
        //console.log('grupo actual: '+idGrupo+' - '+'grupo del empleado: '+listaEmpleados[indice].grupoDTO.id_grupo);

        if (listaEmpleados[indice].grupoDTO === undefined || listaEmpleados[indice].grupoDTO.id_grupo !== idGrupo) {
            $('#nuevosEmpleados').append('<div class="empleado ' + clase + '">' +
                    '<p>' + listaEmpleados[indice].legajo + '</p>' +
                    '<p>' + listaEmpleados[indice].apellidos + ', ' + listaEmpleados[indice].nombres + '</p>' +
                    '<p>' + listaEmpleados[indice].cuil + '</p>' +
                    '<p>' + listaEmpleados[indice].jerarquia.descripcion + '</p>' +
                    '<p id="disponible-' + listaEmpleados[indice].id_empleado + '" value="' + booleanDisponible + '">' + disponible + '</p>' +
                    '<div class="botonesEmpleado" id"check-' + listaEmpleados[indice].id_empleado + '">' +
                    //'<input type="radio" name="capataz" id="'+listaEmpleados[indice].id_empleado+'" value="'+listaEmpleados[indice].id_empleado+'">'+
                    '<input type="checkbox" id="' + listaEmpleados[indice].id_empleado + '" value="' + listaEmpleados[indice].id_empleado + '">' +
                    '</div>' +
                    '</div>');
        }
    }
    if($('#nuevosEmpleados').children().length === 0){
        $('#nuevosEmpleados').append('<h1>No existen empleados disponibles</h1>');
    }
}

function aceptarEmpleados(){
    $('#btnAceptarEmpleados').click(function(){
        var idGrupo = parseInt($('#detalleGrupo').attr('value'));
        
        var empleados = [];
        $('input[type="checkbox"]:checked').each(function() {
          empleados.push(empleado = {id_empleado: $(this).val()});
        });
        
        if(empleados.length === 0){
            mensajeModal('No se ha seleccionado ningun empleado', false, true, false, false);
        }
        else{
            var grupo = {id_grupo: idGrupo, empleados: empleados};
            var solicitud = {mensaje: 'empleados', grupo: JSON.stringify(grupo)};
            
            $.ajax({
                url: '/proyectoconstruccion/SvDetalleGrupoTrabajo', // URL del servlet
                type: 'POST',
                data: solicitud,
                dataType: 'json',
                cache: false,
                success: function (response) {
                    console.log(response);
                    if(response.status === true){
                        mensajeModal(response.mensaje ,false, true, false, false);
                        cargarGrupo(idGrupo);
                        
                        $('#contenedorNuevosEmpleados').hide();
                        $('#nuevosEmpleados').empty();
                        
                        $('#btnAceptarEmpleados').hide();
                        $('#btnCancelarEmpleados').hide();
                        $('btnAgregarEmpleados').show();
                    }
                    else{
                        mensajeModal(response.mensaje ,false, true, false, false);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                }
            });
        }
    });
}

function cancelarEmpleados(){
    $('#btnCancelarEmpleados').click(function(){
        $('#contenedorNuevosEmpleados').hide();
        $('#empleados').empty();
        
        $('#btnCancelarEmpleados').hide();
        $('#btnAceptarEmpleados').hide();
        $('#btnAgregarEmpleados').show();
    });
}