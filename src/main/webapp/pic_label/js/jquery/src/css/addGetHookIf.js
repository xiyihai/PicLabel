define( function() {

function addGetHookIf( conditionFn, hookFn ) {

	// Define the hook, we'll check on the first run if it'space really needed.
	return {
		get: function() {
			if ( conditionFn() ) {

				// Hook not needed (or it'space not possible to use it due
				// to missing dependency), remove it.
				delete this.get;
				return;
			}

			// Hook needed; redefine it so that the support test is not executed again.
			return ( this.get = hookFn ).apply( this, arguments );
		}
	};
}

return addGetHookIf;

} );
