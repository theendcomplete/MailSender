<!doctype html>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>Please create your account</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>

<div class="row">
    <div class="col-sm-6">
        <div class="row">
            <g:uploadForm action="parseExcel" method="post" enctype="multipart/form-data">
                <fieldset class="form">
                    <input type="file" name="file"/>
                </fieldset>
                <fieldset class="btn">
                    <g:submitButton name="doUpload" value="Upload"/>
                </fieldset>
            </g:uploadForm>

        </div>

        <div class="row">
            <div id="allItems">
                <div class="container-fluid" id="table">
                    <div class="row">
                        <div class="span5">
                            <table class="table  table-condensed">
                                <thead>
                                <tr>
                                    <th>email</th>
                                    <th>param1</th>
                                    <th>param2</th>
                                    <th>param3</th>
                                    <th>param4</th>
                                </tr>
                                </thead>
                                <tbody>
                                <div id="quotelist" class="col-md-12">
                                    <g:render template="messageEnries" collection="${messageList}"/>
                                </div>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <form class="form-horizontal" action="startEmailSending" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend>Начать рассылку</legend>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="startSending">Старт!</label>

                    <div class="col-md-4">
                        <button id="startSending" name="startSending" class="btn btn-primary">Старт!</button>
                    </div>
                </div>

            </fieldset>
        </form>

    </div>
</div>

</body>