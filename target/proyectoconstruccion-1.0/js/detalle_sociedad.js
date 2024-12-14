$(document).ready(function(){
    var idSociedad = localStorage.getItem("detalleSociedad");
    
    cargarDetalleSociedad(idSociedad);
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
                insertarSociedad(response);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
}

function insertarSociedad(sociedad){
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
}