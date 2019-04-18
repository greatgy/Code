@echo off
FOR %%A IN (
	"E:\MyWork2\Genius-Server\Base"
	"E:\MyWork2\Genius-Server\Portal"
	"E:\MyWork2\Genius-Server\SNS"
	"E:\MyWork2\Genius-Server\Timer"
	"E:\MyWork2\Genius-Server\ValidCode"
	"E:\MyWork2\Genius-Server\BaseAdmin"
) DO START TortoiseProc.exe /command:update /path:%%A
