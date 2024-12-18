$(document).ready(function(){
    cargarTipoObra();
    cargarSociedades();
    
    cerrarModal('#btnModalObraAceptar');
    
    btnModalNuevaObra();
    btnModalCerrar();
});

function cargarTipoObra(){
    $.ajax({
        url: '/proyectoconstruccion/SvObras', // URL del servlet
        type: 'GET',
        data: {mensaje: 'tipo'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            if(response.mensaje){
                console.log(response.mensaje);
            }
            else{
                insertarTipoObra(response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    }); 
}

function insertarTipoObra(listaTipo){
    $.each(listaTipo, function (i, item) {
        $('#selectTipoObra').append('<option value="' + item.id_tipo_obra + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
    });
}

function cargarSociedades(){
    $.ajax({
        url: '/proyectoconstruccion/SvObras', // URL del servlet
        type: 'GET',
        data: {mensaje: 'sociedades'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            if(response.mensaje){
                console.log(response.mensaje);
            }
            else{
                insertarSociedades(response);
                detectarCambiosSociedades(response);
                capturarDatosObra(response);
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    }); 
}

function insertarSociedades(listaSociedades){
    $('#selectSociedadesObra').append('<option value="0" selected>Seleccione una sociedad</option>');
    $.each(listaSociedades, function (i, item) {
        $('#selectSociedadesObra').append('<option value="' + item.id_sociedad + '" id="' + item.nombre + '">' + item.nombre + '</option>');
    });
}

function detectarCambiosSociedades(listaSociedades){
    const selectElement = document.getElementById('selectSociedadesObra'); // o document.querySelector('#miSelect')

    selectElement.addEventListener('change', (event) => {
        const selectedOption = event.target.options[event.target.selectedIndex];

        var capturarIdSociedad = parseInt($('#selectSociedadesObra').val());
        //console.log('Valor seleccionado:' + idSociedad);
        
        for (const sociedad of listaSociedades) {
            if (sociedad.id_sociedad === capturarIdSociedad) {
                insertarSociedad(sociedad);
                break;
            }
        }
    });
}

function insertarSociedad(sociedad){
    var fechaActual = moment().format('YYYY-MM-DD');
    
    $('#cuitSociedadNuevaObra').val(sociedad.cuit_sociedad);
    
    var listaSeguros = sociedad.segurosDTO;
    
    for(indice=0; indice<listaSeguros.length; indice++){
        // string fecha de vencimiento
        var vencimientoSeguro = moment(listaSeguros[indice].fecha_vencimiento);
        //console.log('fecha actual: '+fechaActual+' - vencimiento: '+vencimientoSeguro);
        var diferenciaDias = vencimientoSeguro.diff(fechaActual, 'days');
        //console.log('el seguro cuenta con '+diferenciaDias+' dias antes de su vencimiento');
        
        if(diferenciaDias >= 0 && diferenciaDias <= 365){
            $('#nombreSeguroNuevaObra').val(listaSeguros[indice].nombre);
            $('#polizaNuevaObra').val(listaSeguros[indice].numero_poliza);
            $('#fechaVigenciaPoliza').val(listaSeguros[indice].fecha_contratacion);
            $('#fechaVencimientoPoliza').val(listaSeguros[indice].fecha_vencimiento);
        }
    }   
}

function capturarDatosObra(listaSociedades){
    $('#btnGuardarObra').click(function(){
        var capturarIdSociedad = parseInt($('#selectSociedadesObra').val());
        
        var sociedadSeleccionada ='';
        
        if(capturarIdSociedad === 0){
            mensajeModal('Debe seleccionar una sociedad paraa esta obra', true, false, false);
        }
        else{
            for (const sociedad of listaSociedades) {
                if (sociedad.id_sociedad === capturarIdSociedad) {
                    sociedadSeleccionada = sociedad;
                    break;
                }
            }
            //console.log(sociedadSeleccionada);
            
            var nombreObra = $('#nombreNuevaObra').val();
            var dgroc = $('#expedienteDGROCnuevaObra').val();
            var dgfyco = $('#expedienteDGFYCOnuevaObra').val();
            var superficie = parseInt($('#superficieNuevaObra').val());
            var calle = $('#direccionNuevaObra').val();
            var altura = parseInt($('#alturaNuevaObra').val());
            var localidad = $('#localidadNuevaObra').val();
            var fechaInicio = $('#fechaInicioObra').val();
            
            var idTipo = parseInt($('#selectTipoObra').val());
            var descripcion = $('#selectTipoObra option:selected').html();
            
            var tipoObra = {id_tipo_obra: idTipo, descripcion: descripcion};
            
            var obra = {nombre_obra: nombreObra, expediente_dgroc: dgroc, expediente_dgfyco: dgfyco,
                        superficie_m2: superficie, calle: calle, altura: altura, localidad: localidad,
                        fecha_inicio: fechaInicio, sociedadObra: sociedadSeleccionada, tipo_obra: tipoObra};
            console.log(obra);
            guardarNuevaObra(obra);
        }
    });
}

function guardarNuevaObra(obra){
    $.ajax({
        url: '/proyectoconstruccion/SvObras', // URL del servlet
        type: 'POST',
        data: {mensaje: 'nuevaObra', obra: JSON.stringify(obra)},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response.mensaje);
            mensajeModal(response.mensaje, false, true, true)
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    }); 
}

function mensajeModal(mensaje, btnAceptar, btnNuevaObra, btnCerrar){
    // borrar el mensaje anterior, si es que lo tiene
    $('#mensajeModalNuevaObra').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    $('#btnModalDetalleAceptar').show();

    if(btnAceptar === true){
        $('#btnModalObraAceptar').show();
    }
    else{
        $('#btnModalObraAceptar').hide();
    }

    if(btnNuevaObra === true){
        $('#btnModalObraNueva').show();
    }
    else{
        $('#btnModalObraNueva').hide();
    }

    if(btnCerrar === true){
        $('#btnModalObraCerrar').show();
    }
    else{
        $('#btnModalObraCerrar').hide();
    }
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalNuevaObra').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();
    });
}

function btnModalNuevaObra(){
    $('#btnModalObraNueva').click(function(){
        $('#mensajeModalNuevaObra').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();

        $('#nombreNuevaObra').val('');
        $('#expedienteDGROCnuevaObra').val('');
        $('#expedienteDGFYCOnuevaObra').val('');
        $('#superficieNuevaObra').val('');
        $('#direccionNuevaObra').val('');
        $('#alturaNuevaObra').val('');
        $('#localidadNuevaObra').val('');
        $('#fechaInicioObra').val('');
        
        $('#nombreSeguroNuevaObra').val('');
        $('#polizaNuevaObra').val('');
        $('#fechaVigenciaPoliza').val('');
        $('#fechaVencimientoPoliza').val('');
    });
}

function btnModalCerrar(){
    $('#btnModalObraCerrar').click(function(){
        window.location.href = "/proyectoconstruccion/vistas/sistemas/obras.jsp";
    });
}