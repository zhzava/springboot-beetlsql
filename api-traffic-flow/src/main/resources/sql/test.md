queryPage
===
	select  
	@pageTag(){
	t.*
	@}  
	FROM  test t where
	#use("condition")#
    
sample
===
* 注释

	select #use("cols")# from TEST  where  #use("condition")#

cols
===
	ID,NAME,ADDRESS,AGE

updateSample
===
	
	ID=#id#,NAME=#name#,ADDRESS=#address#,AGE=#age#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and ID=#id#
	@}
	@if(!isEmpty(name)){
	 and NAME like #'%'+name+'%'#
	@}
	@if(!isEmpty(address)){
	 and ADDRESS like #'%'+address+'%'#
	@}
	@if(!isEmpty(age)){
	 and AGE=#age#
	@}
	