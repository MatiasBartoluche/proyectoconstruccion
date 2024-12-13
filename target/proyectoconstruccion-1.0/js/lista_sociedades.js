$(document).ready(function(){
    cargarSociedades();
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
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
}
