$(document).ready(function(){
    capturarDatosSeguro();
    
    cargarSociedades();
    
    cerrarModal('#btnModalSeguroAceptar');
    cerrarModal('#btnModalSeguroCerrar');
    
    verificarVencimientos();
});

function cargarSociedades(){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'GET',
        data: {mensaje: 'sociedades'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            insertarSociedadesSelect(response);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });  
}

function insertarSociedadesSelect(listaSociedades){
    $.each(listaSociedades, function (i, item) {
        $('#selectSociedades').append('<option value="' + item.id_sociedad + '" id="' + item.nombre + '">' + item.nombre + '</option>');
    });
}

function capturarDatosSeguro(){
    $('#btnGuardarSeguro').click(function(){
        var nombreSeguro = $('#nombreNuevoSeguro').val();
        var digitoGlobalSeguro = $('#digitoGlobalNuevoSeguro').val();
        var cuerpoCuitSeguro = $('#cuerpoCuitNuevoSeguro').val();
        var digitoVerificadorSeguro = $('#digitoVerificadorNuevoSeguro').val();
        var numeroPoliza = $('#numeroPoliza').val();
        var fechaContratacionSeguro = $('#fechaContratacion').val();
        var fechaVencimientoSeguro = $('#fechaVencimiento').val();
        
        // objeto "seguro" inicialmente vacio
        var seguro = {};
        // variables usadas para validar campos
        var verificarNombre = false;
        var verificarGlobal = false;
        var verificarCuerpo = false;
        var verificarDigito = false;
        const tiposPermitidos = [20, 30, 40, 60];
        
        // verificando datos 
        if (nombreSeguro === '') {
            $('#mensajesAdvertencia').append('<p>Debe ingresar un nombre</p>');
            verificarNombre = false;
        } else {
            verificarNombre = true;
            $.extend(seguro, {nombre: nombreSeguro});
        }

        if (!tiposPermitidos.includes(parseInt(digitoGlobalSeguro)) || digitoGlobalSeguro.length !== 2) {
            $('#mensajesAdvertencia').append('<p>El digito global debe ser 20, 30, 40 o 60</p>');
            verificarGlobal = false;
        } else {
            verificarGlobal = true;
        }

        // Validar el segundo campo (número)
        if (!/^\d{8}$/.test(cuerpoCuitSeguro)) {
            $('#mensajesAdvertencia').append('<p>El cuerpo del CUIT debe contener exactamente 8 dígitos</p>');
            verificarCuerpo = false;
        } else {
            verificarCuerpo = true;
        }

        // Validar el tercer campo (verificador)
        if (!/^\d{1}$/.test(digitoVerificadorSeguro)) {
            $('#mensajesAdvertencia').append('<p>El digito verificador debe contener exactamente 8 dígitos</p>');
            verificarDigito = false;
        } else {
            verificarDigito = true;
        }
        
        if(numeroPoliza === ''){
            $('#mensajesAdvertencia').append('<p>Debe ingresar el numero de poliza</p>');
        }
        
        if(verificarNombre === true && verificarGlobal === true && verificarCuerpo === true 
                && verificarDigito === true && numeroPoliza !== '' && fechaContratacionSeguro !== ''
                && fechaVencimientoSeguro !== ''){
            var idSociedad = parseInt($('#selectSociedades').val());
            
            $.extend(seguro, {cuit: digitoGlobalSeguro+'-'+cuerpoCuitSeguro+'-'+digitoVerificadorSeguro});
            $.extend(seguro, {numero_poliza: numeroPoliza});
            $.extend(seguro, {fecha_contratacion: fechaContratacionSeguro});
            $.extend(seguro, {fecha_vencimiento: fechaVencimientoSeguro});
            console.log(seguro);
            solicitudAjaxSeguros(seguro, idSociedad);
        }
        else{
            console.log('datos incompletos');
            $('#mensajesAdvertencia').append('<p>Debe ingresar fechas de contratacion y vencimiento</p>');
            mensajeModal('Verifique estos campos antes de registrar una nueva sociedad', true, false);
        }
    });
}

function solicitudAjaxSeguros(seguro, idSociedad){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'POST',
        data: {mensaje: 'nuevoSeguro', seguro: JSON.stringify(seguro), idSociedad: idSociedad},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response.mensaje);
            mensajeModal(response.mensaje, false, true);
            vaciarCampos();
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function mensajeModal(mensaje, btnAceptar, btnCerrar){
    // borrar el mensaje anterior, si es que lo tiene
    $('#mensajeModalDetalleSociedad').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    $('#btnModalDetalleAceptar').show();

    if(btnAceptar === true){
        $('#btnModalSeguroAceptar').show();
    }
    else{
        $('#btnModalseguroAceptar').hide();
    }

    if(btnCerrar === true){
        $('#btnModalSeguroCerrar').show();
    }
    else{
        $('#btnModalseguroCerrar').hide();
    }
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalDetalleSociedad').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();
    });
}

function vaciarCampos(){
    $('#nombreNuevoSeguro').val('');
    $('#digitoGlobalNuevoSeguro').val('');
    $('#cuerpoCuitNuevoSeguro').val('');
    $('#digitoVerificadorNuevoSeguro').val('');
    $('#numeroPoliza').val('');
    $('#fechaContratacion').val('');
    $('#fechaVencimiento').val('');
}

function verificarVencimientos(){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'GET',
        data: {mensaje: 'seguros'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            if(response.mensaje){
                console.log(response.mensaje);
            }
            else{
                verificarVencimientoSeguros(response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function verificarVencimientoSeguros(listaSeguros){
    var fechaActual = moment().format('YYYY-MM-DD'); 
    var vencimientoSeguros = [];
    
    for(indice=0; indice<listaSeguros.length; indice++){
        // string fecha de vencimiento
        var vencimientoSeguro = moment(listaSeguros[indice].fecha_vencimiento);
        //console.log('fecha actual: '+fechaActual+' - vencimiento: '+vencimientoSeguro);
        var diferenciaDias = vencimientoSeguro.diff(fechaActual, 'days');
        //console.log('el seguro cuenta con '+diferenciaDias+' dias antes de su vencimiento');
        
        if(diferenciaDias >= 0 && diferenciaDias <= 90){
            $('#listaVencimientoSeguros').empty();
            vencimientoSeguros.push(listaSeguros[indice]);
        }
    }
    insertarVencimientoSeguros(vencimientoSeguros);
}

function insertarVencimientoSeguros(listaSeguros){
    var clase = 'impar';
    
    for(indice=0; indice<listaSeguros.length; indice++){
            if(indice%2 === 0){
                clase = 'impar';
            }
            else{
                clase = 'par';
            }
            
            $('#listaVencimientoSeguros').append('<div class="seguro '+clase+'">'+
                                        '<p>'+listaSeguros[indice].nombre+'</p>'+
                                        '<p>'+listaSeguros[indice].cuit+'</p>'+
                                        '<p>'+listaSeguros[indice].numero_poliza+'</p>'+
                                        '<p>'+listaSeguros[indice].sociedadDTO.nombre+'</p>'+
                                        '<p>'+listaSeguros[indice].fecha_vencimiento+'</p>'+
                                    '</div>');
    }
}

/**

*/