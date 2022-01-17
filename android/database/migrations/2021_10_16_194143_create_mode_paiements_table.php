<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateModePaiementsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('mode_paiements', function (Blueprint $table) {
            $table->id();
            $table->string('mode');
            $table->string('solde');
            $table->date('date');
            $table->integer('numero');
            $table->bigInteger('vente_id')->unsigned()->nullable();
            $table->foreign('vente_id')->references('id')->on('ventes');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('mode_paiements');
    }
}
