$(document).ready(function(){
    capturarDatosSociedad();
    
    cerrarModal()
});


function capturarDatosSociedad(){
    $('#guardarNuevaSociedad').click(function(){
        var nombreSociedad = $('#nombreNuevaSociedad').val();
        var digitoGlobalSociedad = $("#digitoGlobalSociedad").val();
        var cuerpoCuit = $("#cuerpoCuitSociedad").val();
        var digitoVerificadorSociedad = $("#digitoVerificadorSociedad").val();
        var razonSocial = $('#razonSocialSociedad').val();
        var calleSociedad = $('#calleSociedad').val();
        var alturaSociedad = $('#alturaSociedad').val();
        var pisoSociedad = $('#pisoSociedad').val();
        var localidadSociedad = $('#localidadSociedad').val();
        var telefonoSociedad = $('#telefonoSociedad').val();
        var mailSociedad = $('#mailSociedad').val();
        // objeto "Sociedad" inicialmente vacio
        var sociedad = {};
        // variables usadas para validar campos
        var verificarNombre = false;
        var verificarGlobal = false;
        var verificarCuerpo = false;
        var verificarDigito = false;
        const tiposPermitidos = [20, 30, 40, 60];
                
        if(nombreSociedad === ''){
            $('#mensajesAdvertencia').append('<p>Debe ingresar un nombre</p>');
            verificarNombre = false;
        }
        else{
            verificarNombre = true;
            $.extend(sociedad, {nombre: nombreSociedad});
        }
        
        if (!tiposPermitidos.includes(parseInt(digitoGlobalSociedad)) || digitoGlobalSociedad.length !== 2) {
            $('#mensajesAdvertencia').append('<p>El digito global debe ser 20, 30, 40 o 60</p>');
            verificarGlobal = false;
        }
        else{
            verificarGlobal = true;
        }

        // Validar el segundo campo (número)
        if (!/^\d{8}$/.test(cuerpoCuit)) {
            $('#mensajesAdvertencia').append('<p>El cuerpo del CUIT debe contener exactamente 8 dígitos</p>');
            verificarCuerpo = false;
        }
        else{
            verificarCuerpo = true;
        }

        // Validar el tercer campo (verificador)
        if (!/^\d{1}$/.test(digitoVerificadorSociedad)) {
            $('#mensajesAdvertencia').append('<p>El digito verificador debe contener exactamente 8 dígitos</p>');
            verificarDigito = false;
        }
        else{
            verificarDigito = true;
        }
        
        if(razonSocial !==''){
            $.extend(sociedad,{razon_social: razonSocial});
        }
        
        if(calleSociedad !==''){
            $.extend(sociedad,{calle: calleSociedad});
        }
        
        if(alturaSociedad !==''){
            $.extend(sociedad,{altura: alturaSociedad});
        }

        if(pisoSociedad !==''){
            $.extend(sociedad,{piso: pisoSociedad});
        }
        
        if(localidadSociedad !==''){
            $.extend(sociedad,{localidad: localidadSociedad});
        }
        
        if(telefonoSociedad !==''){
            $.extend(sociedad,{telefono: telefonoSociedad});
        }
        
        if(mailSociedad !==''){
            $.extend(sociedad,{mail: mailSociedad});
        }
        
        // verificar si tanto cuit como nombre son campos validados
        // para proceder con la solicitud ajax
        if(verificarNombre === true && verificarGlobal === true && verificarCuerpo === true && verificarDigito === true){
            $.extend(sociedad, {cuit_sociedad: digitoGlobalSociedad+'-'+cuerpoCuit+'-'+digitoVerificadorSociedad});
            console.log(sociedad);
            
            //solicitudAjax('nuevo', sociedad);
        }
        else{
            mensajeModal('Verifique estos campos antes de registrar una nueva sociedad', true, false, false, false);
        }
    });
}

function SolicitudAjax(mensaje, sociedad){
        $.ajax({
            url: '/proyectoconstruccion/SvSociedades', // URL del servlet
            type: 'POST',
            data: {mensaje: mensaje, sociedad: JSON.stringify(sociedad)},
            dataType: 'json',
            cache: false,
            success: function (response) {
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
}

// se le pasa como parametro tres boolean, para determinar la visualizacion del boton
function mensajeModal(mensaje, btnCambiar, btnAceptar, btnCancelar, btnEliminar){
    // borrar el mensaje anterior, si es que lo tiene
    
    
    $('#mensajeModalNuevaSociedad').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    
    //
    if(btnCambiar === true){
        $('#btnModalCerrar').show();
    }
    else{
        $('#btnModalcerrar').hide();
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

function cerrarModal(){
    $('#btnModalCerrar').click(function(){
        $('#mensajeModalNuevaSociedad').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();
    });
}