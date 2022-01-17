<?php

namespace App\Http\Controllers;

use Illuminate\Support\Str;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Models\Mpoublier;
use App\Models\User;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Password;
use Illuminate\Auth\Events\PasswordReset;
use Illuminate\Support\Facades\Mail;
use Illuminate\Validation\Rules\Password as RulesPassword;
use Illuminate\Validation\ValidationException;
use Nette\Utils\Random;


class NewPasswordController extends Controller
{
    public function forgotPassword(Request $request)
    {
        $request->validate([
            'email'=>'required|string|email|max:255|exists:users|email',
            'password'=>'required|min:6'
        ]);

        $user=User::where('email',$request->email)->first();
        if($user){
            $user->update(['password'=>hash::make($request->password)]);

            return response()->json($user);

            // $to_name = $user->nom .$user->prenom;
            // $to_email = $user->email;
            // $data1 = array([
            //     'name'=>'Ogbonna Vitalis(khady kébé)',
            //     'body' => 'A test mail']);
            // Mail::send('emails.mail', $data1, function($message) use ($to_name, $to_email) {
            //     $message->to($to_email, $to_name)
            //     ->subject('laravel');
            //     $message->from('khadykebe1195@gmail.com','Test Mail');
            // });

            // return response()->json([
            //     'data' => $data
            // ]);
        }

    }

    public function updatePassword(Request $request){
        $this->validate($request,[

            'email'=>'required|string|email|max:255',
            'new_password '=> 'required|min:6',
            'code'=> 'required',

        ]);

        $data = Mpoublier::where('email',$request->email and 'code',$request->code)->first();
        $user=User::where('email',$request->email)->first();

        if($data && $user){
            $user->password = hash::make($request->new_password);
            return response()->json([
                'user' =>$user
            ]);
        }

    }
}
