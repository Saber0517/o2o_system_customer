/**
 * Simple Map
 *
 *
 * var m = new Map();
 * m.put('key','value');
 * ...
 * var s = "";
 * m.each(function(key,value,index){
 *      s += index+":"+ key+"="+value+"/n";
 * });
 * alert(s);
 *
 * @author dewitt
 * @date 2008-05-24
 */
function Map() {
    /** ??????(????) */
    this.keys = new Array();
    /** ???? */
    this.data = new Object();

    /**
     * ???????
     * @param {String} key
     * @param {Object} value
     */
    this.put = function(key, value) {
        if(this.data[key] == null){
            this.keys.push(key);
        }
        this.data[key] = value;
    };

    /**
     * ????????
     * @param {String} key
     * @return {Object} value
     */
    this.get = function(key) {
        return this.data[key];
    };

    /**
     * ???????
     * @param {String} key
     */
    this.remove = function(key) {
        this.keys.remove(key);
        this.data[key] = null;
    };

    /**
     * ??Map,??????
     *
     * @param {Function} ???? function(key,value,index){..}
     */
    this.each = function(fn){
        if(typeof fn != 'function'){
            return;
        }
        var len = this.keys.length;
        for(var i=0;i<len;i++){
            var k = this.keys[i];
            fn(k,this.data[k],i);
        }
    };

    /**
     * ??????(??Java?entrySet())
     * @return ????{key,value}???
     */
    this.entrys = function() {
        var len = this.keys.length;
        var entrys = new Array(len);
        for (var i = 0; i < len; i++) {
            entrys[i] = {
                key : this.keys[i],
                value : this.data[i]
            };
        }
        return entrys;
    };

    /**
     * ??Map????
     */
    this.isEmpty = function() {
        return this.keys.length == 0;
    };

    /**
     * ???????
     */
    this.size = function(){
        return this.keys.length;
    };

    /**
     * ??toString
     */
    this.toString = function(){
        var s = "{";
        for(var i=0;i<this.keys.length;i++,s+=','){
            var k = this.keys[i];
            s += k+"="+this.data[k];
        }
        s+="}";
        return s;
    };
}