<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class DepotSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $depot = \App\Models\Depot::factory()->create([
            'nom' =>'Pharmacie du Stade',
            'adresse'=>'Route des HLM Rufisaue',
            'telephone'=>'33 836 78 65',
        ]);
    }
}
