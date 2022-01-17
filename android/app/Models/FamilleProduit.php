<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class FamilleProduit extends Model
{
    use HasFactory;
    protected $fillable = [
        'nomFamille',
        
    ];

    public function familleProduit()
    {
        return $this->hasMany(Produit::class);
    }
}
