/*
 * (c) Copyright 2015 EVRYTHNG Ltd London / Zurich
 * www.evrythng.com
 */
package com.evrythng.thng.resource.model.store;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class BatchPopulatingTask extends TaskOnBatch {

	private static final long serialVersionUID = 2514640549756857875L;
	// product in here
	private Result result;
	public static final String FIELD_RESULT = "result";
	private InputParameters inputParameters;
	public static final String FIELD_INPUT_PARAMETERS = "inputParameters";
	private OutputParameters outputParameters;
	public static final String FIELD_OUTPUT_PARAMETERS = "outputParameters";
	private Progress progress;
	public static final String FIELD_PROGRESS = "progress";

	public BatchPopulatingTask() {

		setType(Type.POPULATING);
	}

	public static final class Progress {

		public static final String FIELD_CONTRIBUTIONS = "contributions";
		private Map<String, Contribution> contributions;
		private Integer totalAmount;

		public Progress() {

			contributions = new HashMap<>();
		}

		@JsonIgnore
		public Integer getThngsCount() {

			return contributions.values().stream().map(new Function<Contribution, Integer>() {

				@Override
				public Integer apply(final Contribution contribution) {

					return contribution.getThngsCount();
				}
			}).collect(Collectors.summingInt(new ToIntFunction<Integer>() {

				@Override
				public int applyAsInt(final Integer value) {

					return value;
				}
			}));
		}

		@JsonIgnore
		public Integer getUrlBindingsCount() {

			return contributions.values().stream().map(new Function<Contribution, Integer>() {

				@Override
				public Integer apply(final Contribution contribution) {

					return contribution.getUrlBindingsCount();
				}
			}).collect(Collectors.summingInt(new ToIntFunction<Integer>() {

				@Override
				public int applyAsInt(final Integer value) {

					return value;
				}
			}));
		}

		public Integer getTotalAmount() {

			return totalAmount;
		}

		public void setTotalAmount(final Integer totalAmount) {

			this.totalAmount = totalAmount;
		}

		public Map<String, Contribution> getContributions() {

			return contributions;
		}

		public void setContributions(final Map<String, Contribution> contributions) {

			this.contributions = contributions;
		}

		@JsonIgnore
		public boolean isComplete() {

			return getThngsCount() >= totalAmount && getUrlBindingsCount() >= totalAmount;
		}

		public static final class Contribution {

			public static final String FIELD_URL_BINDINGS = "urlBindings";
			private int urlBindings;
			public static final String FIELD_THNGS = "thngs";
			private int thngs;

			@JsonIgnore
			public void addCreatedThngsCount(final int count) {

				thngs += count;
			}

			@JsonIgnore
			public void addCreatedUrlBindingsCount(final Integer count) {

				urlBindings += count;
			}

			public Integer getThngsCount() {

				return thngs;
			}

			public Integer getUrlBindingsCount() {

				return urlBindings;
			}

			public void setThngs(final int thngs) {

				this.thngs = thngs;
			}

			public void setUrlBindings(final int urlBindings) {

				this.urlBindings = urlBindings;
			}
		}
	}

	public InputParameters getInputParameters() {

		return inputParameters;
	}

	public void setInputParameters(final InputParameters inputParameters) {

		this.inputParameters = inputParameters;
	}

	public Result getResult() {

		return result;
	}

	public void setResult(final Result result) {

		this.result = result;
	}

	public Progress getProgress() {

		return progress;
	}

	public void setProgress(final Progress progress) {

		this.progress = progress;
	}

	public OutputParameters getOutputParameters() {

		return outputParameters;
	}

	public void setOutputParameters(final OutputParameters outputParameters) {

		this.outputParameters = outputParameters;
	}

	public static interface InputParameters {

		String FIELD_TYPE = "type";
		public static final String FIELD_SHORT_DOMAIN = "shortDomain";
		public static final String FIELD_GENERATE_THNGS = "generateThngs";
		public static final String FIELD_GENERATE_REDIRECTIONS = "generateRedirections";
		public static final String FIELD_DEFAULT_REDIRECT_URL = "defaultRedirectUrl";
		public static final String FIELD_THNG_TEMPLATE = "thngTemplate";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			FIXED_AMOUNT, FILE_BASED, LIST_BASED
		}

		Boolean getGenerateThngs();

		Boolean getGenerateRedirections();

		String getShortDomain();

		String getDefaultRedirectUrl();

		ThngTemplate getThngTemplate();

		void setShortDomain(String shortDomain);

		void setDefaultRedirectUrl(String defaultRedirectUrl);

		void setThngTemplate(ThngTemplate thngTemplate);
	}

	public static interface OutputParameters {

		String FIELD_TYPE = "type";

		Type getType();

		void setType(final Type type);

		public static enum Type {

			CSV(Format.CSV);
			private final Format format;

			private Type(final Format format) {

				this.format = format;
			}

			public Format getFormat() {

				return format;
			}
		}
		
		public static enum Format {
			
			CSV("csv", "text/csv");
			private final String extension;
			private final String mimeType;

			private Format(final String extension, final String mimeType) {

				this.extension = extension;
				this.mimeType = mimeType;
			}

			public String getExtension() {

				return extension;
			}

			public String getMimeType() {

				return mimeType;
			}
		}

		public static enum Column {

			SHORT_ID("shortId"), THNG("thng");
			private final String name;

			private Column(final String name) {

				this.name = name;
			}

			public String getName() {

				return name;
			}
		}

		List<Column> getColumns();
	}

	public static final class Result extends BaseTaskResult {

		private Long totalCount;
		private String location;
		private OutputParameters.Format format;
		private Map<String, String> headers;

		public Long getTotalCount() {

			return totalCount;
		}

		public void setTotalCount(final Long totalCount) {

			this.totalCount = totalCount;
		}

		public String getLocation() {

			return location;
		}

		public void setLocation(final String location) {

			this.location = location;
		}

		public OutputParameters.Format getFormat() {

			return format;
		}

		public void setFormat(final OutputParameters.Format format) {

			this.format = format;
		}

		public Map<String, String> getHeaders() {

			return headers;
		}

		public void setHeaders(final Map<String, String> headers) {

			this.headers = headers;
		}
	}
}
