@echo off
FOR %%A IN (
    "E:\MyWork2\Genius-XO\Base"
	"E:\MyWork2\Genius-XO\BaseAdmin"
	"E:\MyWork2\Genius-XO\Portal"
	"E:\MyWork2\Genius-XO\Mongodb"
	"E:\MyWork2\Genius-XO\SNS"
) DO START TortoiseProc.exe /command:update /path:%%A