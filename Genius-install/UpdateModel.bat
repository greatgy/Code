@echo off
FOR %%A IN (
	"E:\MyWork2\Genius-model\Base"
	"E:\MyWork2\Genius-model\BaseAdmin"
	"E:\MyWork2\Genius-model\Portal"
	"E:\MyWork2\Genius-model\SNS"
) DO START TortoiseProc.exe /command:update /path:%%A