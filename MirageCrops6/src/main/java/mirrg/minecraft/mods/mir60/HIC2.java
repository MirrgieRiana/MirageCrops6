package mirrg.minecraft.mods.mir60;

import ic2.api.recipe.IMachineRecipeManager;

import java.util.Optional;

public class HIC2
{

	/**
	 * @return empty: IC2が入ってない・APIが変わった・APIが未初期化の場合
	 */
	public static Optional<IMachineRecipeManager> getMachineRecipeManager(String machine)
	{
		try {
			return Optional.of((IMachineRecipeManager) Class.forName("ic2.api.recipe.Recipes").getField(machine).get(null));
		} catch (IllegalArgumentException
			| IllegalAccessException
			| NoSuchFieldException
			| SecurityException
			| ClassNotFoundException e) {
			return Optional.empty();
		}
	}

}
