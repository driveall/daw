<html>
    <head>
        <title>${title}DriveAllWars - free online game</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css"/>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#name').blur(function(){                
                    $.ajax({
                        url: 'check',
                        data:({name:$('#name').val()}),
                        success:function(data){
                            $('#isUserValue').html(data);
                        }
                    });
                })
            });            
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#name').focus(function(){                
                    $('#isUserValue').html('');
                })
            });            
        </script>
        <script type="text/javascript">
            function doPassCheck(){                
                $.ajax({
                    url: 'check',
                    data:({password:$('#password').val()}),
                    success:function(data){
                        $('#strengthPassValue').html(data);
                    }
                });
            }            
        </script>        
    </head>
    <body>
        <div align="center">
            ${message}
        </div>
        <div align="center">
            ${content}
        </div>
    </body>
</html>