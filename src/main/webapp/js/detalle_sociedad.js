$(document).ready(function(){
    var idSociedad = localStorage.getItem("detalleSociedad");
    
    cargarDetalleSociedad(idSociedad);

    modificarSociedad();
    guardarNuevosDatosSociedad();
    nuevoSeguro();
    cancelarSeguro();
    
    toggleListas('#btnHistorialSeguros', '#historialSeguros', 'historial de seguros');
    toggleListas('#btnHistorialObras', '#historialObras', 'historial de obras');
    
    cerrarModal('#btnModalDetalleAceptar');
    cerrarModal('#btnModalDetalleCerrar');
    cerrarModal('#btnModalDetalleCancelar');
    
    //btnDetalleSeguro();
    btnBorrarSeguro();
});

function cargarDetalleSociedad(idSociedad){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'GET',
        data: {mensaje: 'detalleSociedad', idSociedad: idSociedad},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response);
            if (response.mensaje) {
                console.log(response.mensaje);
            } else {
                insertarSociedad(response);
                // en este momento llamo a la funcion cancelarDatosSociedad
                // y le paso como parametro el objeto que obtuve de la solicitud
                // para poder recargar los datos en la pagina en caso de que el usuario
                // los haya modificado en el formulario pero cancelado su modificacion
                cancelarDatosSociedad(response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function insertarSociedad(sociedad){
    // vacial lista de seguros
    $('#listaSeguros').empty();
    $('#detalleSociedad').attr('value', sociedad.id_sociedad);
    
    var cuit = sociedad.cuit_sociedad.split('-');
    $('#nombreDetalleSociedad').val(sociedad.nombre);
    
    $('#digitoGlobalDetalleSociedad').val(cuit[0]);
    $('#cuerpoCuitDetalleSociedad').val(cuit[1]);
    $('#digitoVerificadorDetalleSociedad').val(cuit[2]);
    
    if(sociedad.razon_social){
       $('#razonSocialDetalleSociedad').val(sociedad.razon_social); 
    }
    else{
       $('#razonSocialDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.calle){
       $('#calleDetalleSociedad').val(sociedad.calle); 
    }
    else{
       $('#calleDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.altura){
       $('#alturaDetalleSociedad').val(sociedad.altura); 
    }
    else{
       $('#alturaDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.piso){
       $('#pisoDetalleSociedad').val(sociedad.piso); 
    }
    else{
       $('#pisoDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.localidad){
       $('#localidadDetalleSociedad').val(sociedad.localidad); 
    }
    else{
       $('#localidadDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.telefono){
       $('#telefonoDetalleSociedad').val(sociedad.telefono); 
    }
    else{
       $('#telefonoDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.mail){
       $('#mailDetalleSociedad').val(sociedad.mail); 
    }
    else{
       $('#mailDetalleSociedad').attr('placeholder', 'Sin datos'); 
    }
    
    if(sociedad.segurosDTO.length === 0){
        $('#listaSeguros').append('<h1>No hay seguros contratados</h1>');
    }
    else{
        insertarSeguros(sociedad.segurosDTO);
    }
    
    if(sociedad.obrasDTO.length === 0){
        $('#listaObras').append('<h1>No hay obras asociadas a esta sociedad</h1>');
    }
}

function insertarSeguros(listaSeguros){
    var clase = 'impar';
    for(indice=0; indice<listaSeguros.length; indice++){
        if(indice%2 === 0){
            clase = 'impar';
        }
        else{
            clase = 'par';
        }
        
        $('#listaSeguros').append('<div class="seguro '+clase+'">'+
                                    '<p>'+listaSeguros[indice].nombre+'</p>'+
                                    '<p>'+listaSeguros[indice].cuit+'</p>'+
                                    '<p>'+listaSeguros[indice].numero_poliza+'</p>'+
                                    '<p>'+listaSeguros[indice].fecha_contratacion+'</p>'+
                                    '<p>'+listaSeguros[indice].fecha_vencimiento+'</p>'+
                                    '<div class="botonesEmpleado">'+
                                        '<span class="btnIcono btnDetalleSeguro" id="ver-'+listaSeguros[indice].id_seguro+'" data-idSeguro="'+listaSeguros[indice].id_seguro+'">'+
                                            '<i class="bx bx-search-alt icon"></i>'+
                                        '</span>'+
                                        //'<span class="btnIcono btnBorrarSeguro" id="borrar-'+listaSeguros[indice].id_seguro+'" data-idSeguro="'+listaSeguros[indice].id_seguro+'">'+
                                            //'<i class="bx bx-trash icon"></i>'+
                                        //'</span>'+
                                    '</div>'+
                                '</div>');
    }
}

/*
function btnDetalleSeguro(){
    $('#listaSeguros').on('click', '.btnDetalleSeguro', function () {
        const idSeguro = $(this).attr('id').split('-')[1]; // Obtener el legajo del botón
        console.log('id: '+idSeguro);
        //localStorage.setItem('detalleSociedad', idSociedad);
        //window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_sociedad.jsp";
    });
}
*/

function btnBorrarSeguro(){
    $('#listaSeguros').on('click', '.btnBorrarSeguro', function () {
        var idSeguro = $(this).attr('id').split('-')[1]; // Obtener el id del botón
        mensajeModal('¿Esta seguro que desea borrar este seguro?', false, false, true, true);
        btnModalBorrar('borrarSeguro', idSeguro);
    });
}

function btnModalBorrar(mensaje, id){
    $('#btnModalDetalleBorrar').click(function(){
        console.log('borrar:' +mensaje+'-'+id);
        
       $.ajax({
            url: '/proyectoconstruccion/SvSociedades', // URL del servlet
            type: 'POST',
            data: {mensaje: mensaje, id: id},
            dataType: 'json',
            cache: false,
            success: function (response) {
                console.log(response);
                mensajeModal(response.mensaje, true, false, false, false);
                
                var idSociedad = localStorage.getItem("detalleSociedad");
                cargarDetalleSociedad(idSociedad);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
}

function modificarSociedad(){
    $('#modificarSociedad').click(function(){
        $('#modificarSociedad').hide();
        $('#agregarSeguro').hide();
        $('#guardarNuevosDatosSociedad').show();
        $('#cancelarDatosSociedad').show();
        
        $('#agregarSeguro').hide();
        $('#cancelarSeguro').hide();
        $('#nuevoSeguro').hide();
        
        // habilitando campos para la modificacion
        $('#nombreDetalleSociedad').prop('disabled', false);
        $('#digitoGlobalDetalleSociedad').prop('disabled', false);
        $('#cuerpoCuitDetalleSociedad').prop('disabled', false);
        $('#digitoVerificadorDetalleSociedad').prop('disabled', false);

        $('#razonSocialDetalleSociedad').prop('disabled', false);
        $('#calleDetalleSociedad').prop('disabled', false);
        $('#alturaDetalleSociedad').prop('disabled', false);
        $('#pisoDetalleSociedad').prop('disabled', false);
        $('#localidadDetalleSociedad').prop('disabled', false);
        $('#telefonoDetalleSociedad').prop('disabled', false);
        $('#mailDetalleSociedad').prop('disabled', false);
    });
}

function cancelarDatosSociedad(sociedad){
    $('#cancelarDatosSociedad').click(function(){
        console.log('cancelando modificacion de datos');
        $('#modificarSociedad').show();
        $('#agregarSeguro').show();
        $('#guardarNuevosDatosSociedad').hide();
        $('#cancelarDatosSociedad').hide();
        
        // deshabilitando campos para la modificacion
        $('#nombreDetalleSociedad').prop('disabled', true);
        $('#digitoGlobalDetalleSociedad').prop('disabled', true);
        $('#cuerpoCuitDetalleSociedad').prop('disabled', true);
        $('#digitoVerificadorDetalleSociedad').prop('disabled', true);

        $('#razonSocialDetalleSociedad').prop('disabled', true);
        $('#calleDetalleSociedad').prop('disabled', true);
        $('#alturaDetalleSociedad').prop('disabled', true);
        $('#pisoDetalleSociedad').prop('disabled', true);
        $('#localidadDetalleSociedad').prop('disabled', true);
        $('#telefonoDetalleSociedad').prop('disabled', true);
        $('#mailDetalleSociedad').prop('disabled', true);

        // reinserto los datos en caso de que hayan sido modificados
        // y se haya cancelado la modificacion
        insertarSociedad(sociedad);
    });
}

function toggleListas(idBoton, idLista, texto){
    $(idBoton).on('click', function () {
        const $contenido = $(idLista); // Seleccionar el div
        $contenido.toggle(300, function () {
            // Cambiar el texto del botón después de mostrar/ocultar
            var mostrar ='Ver '+texto;
            var ocultar ='Ocultar '+texto;
            
            const textoBoton = $contenido.is(':visible') ? ocultar : mostrar;
            $(idBoton).text(textoBoton);
        });
    });
}


function guardarNuevosDatosSociedad(){
    $('#guardarNuevosDatosSociedad').click(function(){
        console.log('guardar nuevos datos');
        /*$('#modificarSociedad').show();
        $('#guardarNuevosDatosSociedad').hide();
        $('#cancelarDatosSociedad').hide();*/
        
        var idSociedad = $('#detalleSociedad').attr('value');
        
        var nuevoNombreSociedad = $('#nombreDetalleSociedad').val();
        var nuevoDigitoGlobalSociedad = $('#digitoGlobalDetalleSociedad').val();
        var nuevoCuerpoCuitSociedad = $('#cuerpoCuitDetalleSociedad').val();
        var nuevoDigitoVerificadorSociedad = $('#digitoVerificadorDetalleSociedad').val();

        var nuevaRazonSocial = $('#razonSocialDetalleSociedad').val();
        var nuevaCalleSociedad = $('#calleDetalleSociedad').val();
        var nuevaAlturaSociedad = $('#alturaDetalleSociedad').val();
        var nuevoPisoSociedad = $('#pisoDetalleSociedad').val();
        var nuevaLocalidadSociedad = $('#localidadDetalleSociedad').val();
        var nuevoTelefonoSociedad = $('#telefonoDetalleSociedad').val();
        var nuevoMailSociedad = $('#mailDetalleSociedad').val();
        
        // objeto "Sociedad" inicialmente vacio
        var sociedad = {};
        // variables usadas para validar campos
        var verificarNombre = false;
        var verificarGlobal = false;
        var verificarCuerpo = false;
        var verificarDigito = false;
        const tiposPermitidos = [20, 30, 40, 60];
        
        // verificando datos 
        if (nuevoNombreSociedad === '') {
            $('#mensajesAdvertencia').append('<p>Debe ingresar un nombre</p>');
            verificarNombre = false;
        } else {
            verificarNombre = true;
            $.extend(sociedad, {nombre: nuevoNombreSociedad});
        }

        if (!tiposPermitidos.includes(parseInt(nuevoDigitoGlobalSociedad)) || nuevoDigitoGlobalSociedad.length !== 2) {
            $('#mensajesAdvertencia').append('<p>El digito global debe ser 20, 30, 40 o 60</p>');
            verificarGlobal = false;
        } else {
            verificarGlobal = true;
        }

        // Validar el segundo campo (número)
        if (!/^\d{8}$/.test(nuevoCuerpoCuitSociedad)) {
            $('#mensajesAdvertencia').append('<p>El cuerpo del CUIT debe contener exactamente 8 dígitos</p>');
            verificarCuerpo = false;
        } else {
            verificarCuerpo = true;
        }

        // Validar el tercer campo (verificador)
        if (!/^\d{1}$/.test(nuevoDigitoVerificadorSociedad)) {
            $('#mensajesAdvertencia').append('<p>El digito verificador debe contener exactamente 8 dígitos</p>');
            verificarDigito = false;
        } else {
            verificarDigito = true;
        }
        
        if(nuevaRazonSocial ===''){
            nuevaRazonSocial = null;
        }
        
        if(nuevaCalleSociedad ===''){
            nuevaCalleSociedad = null;
        }
        
        if(nuevaAlturaSociedad ==='' || nuevaAlturaSociedad === 0){
            nuevaAlturaSociedad = null;
        }

        if(nuevoPisoSociedad ===''){
            nuevoPisoSociedad = null;
        }
        
        if(nuevaLocalidadSociedad ===''){
            nuevaLocalidadSociedad = null;
        }
        
        if(nuevoTelefonoSociedad ===''){
            nuevoTelefonoSociedad = null;
        }
        
        if(nuevoMailSociedad ===''){
            nuevoMailSociedad = null;
        }
        
        // verificar si tanto cuit como nombre son campos validados
        // para proceder con la solicitud ajax
        if(verificarNombre === true && verificarGlobal === true && verificarCuerpo === true && verificarDigito === true){
            $.extend(sociedad,{id_sociedad: idSociedad});
            $.extend(sociedad, {cuit_sociedad: nuevoDigitoGlobalSociedad+'-'+nuevoCuerpoCuitSociedad+'-'+nuevoDigitoVerificadorSociedad});
            $.extend(sociedad, {razon_social: nuevaRazonSocial});
            $.extend(sociedad, {calle: nuevaCalleSociedad});
            $.extend(sociedad, {altura: nuevaAlturaSociedad});
            $.extend(sociedad, {piso: nuevoPisoSociedad});
            $.extend(sociedad, {localidad: nuevaLocalidadSociedad});
            $.extend(sociedad, {telefono: nuevoTelefonoSociedad});
            $.extend(sociedad, {mail: nuevoMailSociedad});
            
            console.log(sociedad);
            guardarDatosSociedad(sociedad);
            //$('#mensajesAdvertencia').append('<p>Si desea registrar otra sociedad, haga click en el boton "nueva sociedad", de lo contrario, haga click en el boton "Cerrar"</p>');
        }
        else{
            console.log('datos incompletos');
            mensajeModal('Verifique estos campos antes de registrar una nueva sociedad', true, false, false, false);
        }
    });
}

function guardarDatosSociedad(sociedad){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'POST',
        data: {mensaje: 'modificar', sociedad: JSON.stringify(sociedad)},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response.mensaje);
            mensajeModal(response.mensaje, false, true, false, false);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function mensajeModal(mensaje, btnAceptar, btnCerrar, btnBorrar, btnCancelar){
    // borrar el mensaje anterior, si es que lo tiene
    $('#contenedorTextoModal').empty();
    $('#mensajeModalDetalleSociedad').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    $('#btnModalDetalleAceptar').show();

    if(btnAceptar === true){
        $('#btnModalDetalleAceptar').show();
    }
    else{
        $('#btnModalDetalleAceptar').hide();
    }

    if(btnCerrar === true){
        $('#btnModalDetalleCerrar').show();
        $('#modificarSociedad').show();
        $('#agregarSeguro').show();
        $('#guardarNuevosDatosSociedad').hide();
        $('#cancelarDatosSociedad').hide();
    }
    else{
        $('#btnModalDetalleCerrar').hide();
    }
    
    if(btnBorrar === true){
        $('#btnModalDetalleBorrar').show();
    }
    else{
        $('#btnModalDetalleBorrar').hide();
    }
    
    if(btnCancelar === true){
        $('#btnModalDetalleCancelar').show();
    }
    else{
        $('#btnModalDetalleCancelar').hide();
    }
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalDetalleSociedad').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();
    });
}

function nuevoSeguro(){
    $('#agregarSeguro').click(function(){
        $('#agregarSeguro').hide();
        $('#cancelarSeguro').show();
        
        $('#nuevoSeguro').toggle(300, function(){
            $('#nuevoSeguro').css('display', 'flex');
            $('#nuevoSeguro').show();
        });
    });
}

function cancelarSeguro(){
    $('#cancelarSeguro').click(function(){
        $('#cancelarSeguro').hide();
        $('#agregarSeguro').show();
        
        $('#nuevoSeguro').toggle(300, function(){
            $('#nuevoSeguro').hide();
        });
    });
}