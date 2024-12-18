$(document).ready(function(){
    cargarSociedades();
    
    detalleSociedad();
    borrarSociedad();
    
    solicitudAjaxBorrar();
    
    cerrarModal('#btnModalListaCancelar');
    cerrarModal('#btnModalListaCerrar');
    
    window.addEventListener('pageshow', function (event) {
        if (event.persisted || performance.getEntriesByType('navigation')[0].type === 'back_forward') {
            cargarSociedades(); // Volver a cargar los datos de sociedades
        }
    });
});

function cargarSociedades(){
    $.ajax({
        url: '/proyectoconstruccion/SvSociedades', // URL del servlet
        type: 'GET',
        data: {mensaje: 'sociedades'},
        dataType: 'json',
        cache: false,
        success: function (response) {
            console.log(response);
            insertarSociedades(response);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function insertarSociedades(listaSociedades){
    $('#homeListaSociedades').empty();
    for(indice=0; indice<listaSociedades.length; indice++){
        $('#homeListaSociedades').append('<article class="modulo sociedades">'+
                                        '<div class="contenedorImagen">'+
                                            '<i class="bx bx-food-menu icon"></i>'+
                                        '</div>'+
                                        '<div class="infoSociedad">'+
                                            '<p>' + listaSociedades[indice].nombre + '</p>' +
                                            '<p>CUIT: ' + listaSociedades[indice].cuit_sociedad + '</p>' +
                                        '</div>'+
                                        '<div class="botonesSociedad">'+
                                            '<span class="btnIcono btnVerSociedad" id="ver-'+listaSociedades[indice].id_sociedad+'" data-idSociedad="'+listaSociedades[indice].id_sociedad+'">'+
                                                '<i class="bx bx-search-alt icon"></i>'+
                                            '</span>'+
                                            '<span class="btnIcono btnBorrarSociedad" id="borrar-'+listaSociedades[indice].id_sociedad+'" data-idGrupo="'+listaSociedades[indice].id_sociedad+'">'+
                                                '<i class="bx bx-trash icon"></i>'+
                                            '</span>'+
                                        '</div>'+
                                    '</article>');
    }
}

function detalleSociedad(){
    $('#homeListaSociedades').on('click', '.btnVerSociedad', function () {
        const idSociedad = $(this).attr('id').split('-')[1]; // Obtener el legajo del botón
        console.log('id: '+idSociedad);
        localStorage.setItem('detalleSociedad', idSociedad);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_sociedad.jsp";
    });
}

function borrarSociedad(){
    $('#homeListaSociedades').on('click', '.btnBorrarSociedad', function () {
        var idSociedad = $(this).attr('id').split('-')[1]; // Obtener el id del botón
        console.log('borrar: '+idSociedad);
        localStorage.setItem('borrarSociedad', idSociedad);
        
        mensajeModal('¿Esta seguro que desea borrar esta sociedad?', true, true, false);
    });
}

function solicitudAjaxBorrar(){
    $('#btnModalListaBorrar').off().click(function(){
        $('#contenedorTextoModal').empty();
        var idSociedad = localStorage.getItem('borrarSociedad');
        $.ajax({
            url: '/proyectoconstruccion/SvSociedades', // URL del servlet
            type: 'POST',
            data: {mensaje: 'borrarSociedad', idSociedad: idSociedad},
            dataType: 'json',
            cache: false,
            success: function (response) {
                console.log(response);
                mensajeModal(response.mensaje, false, false, true);
                
                cargarSociedades();
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
}

function mensajeModal(mensaje, btnBorrar, btnCancelar, btnCerrar){
    // borrar el mensaje anterior, si es que lo tiene
    $('#mensajeModalListaSociedad').show();
    $('#contenedorTextoModal').append('<p>'+mensaje+'</p>');
    $('#btnModalDetalleAceptar').show();

    if(btnBorrar === true){
        $('#btnModalListaBorrar').show();
    }
    else{
        $('#btnModalListaBorrar').hide();
    }

    if(btnCancelar === true){
        $('#btnModalListaCancelar').show();
    }
    else{
        $('#btnModalListaCancelar').hide();
    }

    if(btnCerrar === true){
        $('#btnModalListaCerrar').show();
    }
    else{
        $('#btnModalListaCerrar').hide();
    }
}

function cerrarModal(idBoton){
    $(idBoton).click(function(){
        $('#mensajeModalListaSociedad').hide();
        $('#contenedorTextoModal').empty();
        $('#mensajesAdvertencia').empty();
    });
}
