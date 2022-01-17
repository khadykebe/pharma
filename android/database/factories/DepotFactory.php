<?php

namespace Database\Factories;

use App\Models\Depot;
use Illuminate\Database\Eloquent\Factories\Factory;

class DepotFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = Depot::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'nom' =>'Pharmacie du Stade',
            'adresse'=>'Route des HLM Rufisaue',
            'telephone'=>'33 836 78 65',
        ];
    }
}
