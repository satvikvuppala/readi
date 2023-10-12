powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'xray_project_key=(.*)', 'xray_project_key=%1%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'xray_test_plan_id=(.*)', 'xray_test_plan_id=%2%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'create_new_test_execution=(.*)', 'create_new_test_execution=%3%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'xray_test_execution_id=(.*)', 'xray_test_execution_id=%4%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'save_results_to_xray=(.*)', 'save_results_to_xray=%5%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties') -replace 'path_tesedata_files=./src/test/java/com/bnpp/testdata/(.*)', 'path_tesedata_files=./src/test/java/com/bnpp/testdata/%6%' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/xray/xray_config.properties'"

powershell -Command "(gc './module-cb/src/test/java/com/bnpp/appium/config/Configurations.java') -replace 'ExecutionEnvnmt = \"(.*)\"', 'TestEnvironment = \"%6%\"' | Out-File -encoding ASCII './module-cb/src/test/java/com/bnpp/appium/config/Configurations.java'"



