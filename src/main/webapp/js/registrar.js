$(document).ready(function(){
    
    cargarRoles();
});

function cargarRoles(){
    console.log("cargar roles");
    
    $.ajax({
        url: 'SvRegistrar', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            // 'data' es un array de objetos Usuario en formato JSON
            console.log(data);
            // Aquí puedes iterar y mostrar los datos en tu página
            
            $.each(data, function(i, item){
                console.log(item.id_rol + " - " + item.descripcion);
                $('#rol').append('<option value="'+item.id_rol+'">'+item.descripcion+'</option>');
            });
            
        },
        error: function(xhr, status, error) {
            console.error('Error al obtener la lista de usuarios:', error);
        }
    });
}

//recibo la respuesta listaRoles e inserto en la pestania desplegable