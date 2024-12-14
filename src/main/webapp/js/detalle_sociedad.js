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
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
}