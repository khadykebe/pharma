<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;
use Spatie\Permission\Models\Role;

class UserAdmin extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // $role1 = Role::create(['name' => 'pharmacien','guard_name'=> 'pharmacien']);
        // $role2 = Role::create(['name' => 'gerant','guard_name'=> 'gerant']);
        // $role3 = Role::create(['name' => 'vendeur','guard_name'=> 'vendeur']);

        $user = \App\Models\User::factory()->create([

            'nom'=>"kebe",
            'prenom'=>"khady",
            'adresse'=>"rufisque",
            'telephone'=>"78 471 42 87",
            'email'=>"khadykebe1195@gmail.com",
            'roles'=>"pharmacien",
            'password'=>Hash::make('alliak58')
        ]);
        $user->assignRole('pharmacien');
        $user->assignRole('gerant');
        $user->assignRole('vendeur');

       
    }
}
