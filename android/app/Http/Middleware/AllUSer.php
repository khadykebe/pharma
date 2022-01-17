<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class AllUSer
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next,string $role)
    {
        if($request->user->roles()->where('name',$role)->exists())
            return $next($request);
        abort(403);
    }
}
