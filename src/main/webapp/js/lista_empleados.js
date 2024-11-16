$(document).ready(function(){
    
    toggleListas('#btnListaObreros', '#listaObreros', 'obreros');
    toggleListas('#btnListaAdministrativos', '#listaOficina', 'administrativos');
    toggleListas('#btnListaSubcontratados', '#listaSubcontratados', 'subcontratados');
    
    cargarEmpleados();
});

function cargarEmpleados(){
    console.log("cargar empleados");
}

function toggleListas(idBoton, idLista, texto){
    $(idBoton).on('click', function () {
        const $contenido = $(idLista); // Seleccionar el div
        $contenido.toggle(300, function () {
            // Cambiar el texto del botón después de mostrar/ocultar
            var mostrar ='';
            var ocultar ='';
            
            if(texto === 'obreros'){
                mostrar = 'Mostrar lista de obreros';
                ocultar = 'Ocultar loista de obreros'; 
            }
            else if(texto === 'administrativos'){
                mostrar = 'Mostrar lista de administrativos';
                ocultar = 'Ocultar loista de administrativos'; 
            }
            else if(texto === 'subcontratados'){
                mostrar = 'Mostrar lista de subcontratados';
                ocultar = 'Ocultar loista de subcontratados'; 
            }
            
            const textoBoton = $contenido.is(':visible') ? ocultar : mostrar;
            $(idBoton).text(textoBoton);
        });
    });
}