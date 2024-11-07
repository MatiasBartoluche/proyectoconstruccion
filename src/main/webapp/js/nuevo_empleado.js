$(document).ready(function(){
    
    cargarJerarquia();
    cargarTiposContratos();
    
    cargarImagen();
});

function cargarJerarquia(){
    console.log("cargar jerarquia");
    $.ajax({
        url: '/proyectoconstruccion/SvNuevoEmpleado', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log(response.jerarquias);
            console.log(response.contratos);
            // 'data' es un array de objetos Usuario en formato JSON
            // Aquí puedes iterar y mostrar los datos en tu página
            $.each(response.jerarquias, function (i, item) {
                $('#jerarquia').append('<option value="' + item.id_jerarquia + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
            });
            
            $.each(response.contratos, function (i, item) {
                $('#contrato').append('<option value="' + item.id_contrato + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
            });
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener la lista de roles:', error);
        }
    });
}

function cargarTiposContratos(){
    console.log("cargar contratos");
}

function cargarImagen(){
    $('#guardarNuevoEmpleado').click(function(){
        const fileInput = document.getElementById("dniEmpleado");
        const file = fileInput.files[0];

        var imagen = null;

        if (file) {
            // Convertir la imagen a base64
            const reader = new FileReader();
            reader.onload = function (event) {
                const base64Image = event.target.result.split(",")[1]; // Remover el encabezado "data:image/png;base64,"
                //console.log(base64Image);
                var imagen = {foto_dni: base64Image};
                capturarDatos(imagen);
            };
            reader.readAsDataURL(file);

            console.log("existe imagen");
        }
        else {
            console.log("no existe imagen");
                var imagen = {foto_dni: null};
                capturarDatos(imagen);
        }
    });
}

function capturarDatos(imagen){    
    //datos personales
    var nombres = $('#nombresEmpleado').val();
    var apellidos = $('#apellidosEmpleado').val();
    var digitoGlobal = $('#digitoGlobal').val();
    var cuerpoCUIL = $('#cuerpoCUIL').val();
    var digitoVerificador = $('#digitoVerificador').val();
    var dniEmpleado = imagen.foto_dni; // base64 de la imagen cargada o null
    
    // datos de contacto
    var calleEmpleado = $('#calleEmpleado').val();
    var numeroDomicilioEmpleado = $('#numeroDomicilioEmpleado').val();
    var pisoEmpleado = $('#pisoEmpleado').val();
    var telefonoEmpleado = $('#telefonoEmpleado').val();
    var telefonoFamiliarEmpleado = $('#telefonoFamiliarEmpleado').val();
    
    //datos administrativos
    var legajo = $('#legajo').val();
    
    var idJerarquia = $('#jerarquia').val();
    var jerarquia = $('#jerarquia option:selected').html();
    
    var idContrato = $('#contrato').val();
    var contrato = $('#contrato option:selected').html();
    var salarioEmpleado = $('#salarioEmpleado').val();
    var fechaIngreso = $('#fechaIngreso').val();
    
    var empleado = {};
    
    /*if(nombres!==""){
        $.extend(empleado, {nombres: nombres});
    }
    else{
        
    }*/

    console.log(empleado);
    
    console.log(idJerarquia);
    console.log(jerarquia);
    
    console.log(idContrato);
    console.log(contrato);
}